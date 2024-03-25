package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public void tryMove(Position start, Position end) {
        Piece movingPiece = findMovingPiece(start);
        validateTeamRule(movingPiece, end);

        boolean hasEnemy = hasEnemy(end);
        List<Position> path = movingPiece.findPath(start, end, hasEnemy);
        validatePath(path);

        move(start, end, movingPiece);
    }

    private void validatePath(List<Position> path) {
        if (isBlocked(path)) {
            throw new IllegalArgumentException("다른 말이 있어 이동 불가능합니다.");
        }
    }

    private boolean hasEnemy(Position end) {
        return find(end).isPresent();
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

    private boolean isBlocked(List<Position> path) {
        return path.stream()
                .anyMatch(board::containsKey);
    }

    private void move(Position start, Position end, Piece movingPiece) {
        board.remove(start);
        board.put(end, movingPiece);
        turn = turn.next();
    }
}
