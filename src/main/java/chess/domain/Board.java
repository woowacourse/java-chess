package chess.domain;

import chess.GameStatus;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Stream;

public class Board {

    private final Map<Position, Piece> board;
    private Team turn;

    public Board(Map<Position, Piece> board) {
        this.board = new HashMap<>(board);
        this.turn = Team.WHITE;
    }

    public Optional<Piece> find(Position position) {
        Piece piece = board.get(position);
        return Optional.ofNullable(piece);
    }

    public GameStatus tryMove(Position start, Position end) {
        Piece movingPiece = findMovingPiece(start);
        validateTeamRule(movingPiece, end);

        boolean hasEnemy = hasEnemy(end);
        List<Position> path = movingPiece.findPath(start, end, hasEnemy);
        validatePath(path);

        return move(start, end, movingPiece);
    }

    private Piece findMovingPiece(Position start) {
        return find(start)
                .orElseThrow(() -> new IllegalArgumentException("해당 위치에 말이 없습니다."));
    }

    private void validateTeamRule(Piece movingPiece, Position end) {
        if (!movingPiece.isSameTeam(turn)) {
            throw new IllegalArgumentException("상대 팀의 차례입니다.");
        }
        if (isSameTeamAtDestination(end)) {
            throw new IllegalArgumentException("같은 팀의 말을 잡을 수 없습니다.");
        }
    }

    private boolean isSameTeamAtDestination(Position end) {
        return find(end).map(piece -> piece.isSameTeam(turn))
                .orElse(false);
    }

    private boolean hasEnemy(Position end) {
        return find(end).map(piece -> !piece.isSameTeam(turn))
                .orElse(false);
    }

    private void validatePath(List<Position> path) {
        if (isBlocked(path)) {
            throw new IllegalArgumentException("다른 말이 있어 이동 불가능합니다.");
        }
    }

    private boolean isBlocked(List<Position> path) {
        return path.stream()
                .anyMatch(board::containsKey);
    }

    private GameStatus move(Position start, Position end, Piece movingPiece) {
        board.remove(start);
        if (find(end).map(piece -> !piece.isSameTeam(turn) && piece.isKing()).orElse(false)) {
            return GameStatus.whenWin(turn);
        }
        board.put(end, movingPiece);
        turn = turn.next();
        return GameStatus.PLAY;
    }

    public double calculateScoreOf(Team team) {
        double basicScore = calculateBasicScoreOf(team);
        double minusScore = calculateMinusScoreOf(team);

        return basicScore - minusScore;
    }

    private double calculateBasicScoreOf(Team team) {
        return board.values().stream()
                .filter(piece -> piece.isSameTeam(team))
                .mapToDouble(PieceType::scoreOf)
                .sum();
    }

    private double calculateMinusScoreOf(Team team) {
        int count = (int) findPawnEntryOf(team)
                .filter(entry -> findPawnEntryOf(team)
                        .anyMatch(otherEntry -> !otherEntry.getKey().equals(entry.getKey())
                                && otherEntry.getKey().isSameFile(entry.getKey())))
                .count();

        return count * 0.5;
    }

    private Stream<Entry<Position, Piece>> findPawnEntryOf(Team team) {
        return board.entrySet().stream()
                .filter(entry -> entry.getValue().isSameTeam(team))
                .filter(entry -> entry.getValue().isPawn());
    }
}
