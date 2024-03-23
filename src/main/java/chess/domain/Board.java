package chess.domain;

import chess.domain.piece.Piece;
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

    public void move(Position start, Position end) {
        validate(start, end);
        Piece piece = board.get(start);
        List<Position> path = piece.findPath(start, end, isExistEnemy(end));

        validateEmpty(path);
        board.remove(start);
        board.put(end, piece);
        turn = turn.nextTurn();
    }

    public void validate(Position start, Position end) {
        if (isNotExistPiece(start)) {
            throw new IllegalArgumentException("해당 위치에 말이 없습니다.");
        }
        if (isDifferentTurn(start)) {
            throw new IllegalArgumentException("해당 팀의 차례가 아닙니다.");
        }
        if (isExistSameTeam(end)) {
            throw new IllegalArgumentException("도착 지점에 같은 팀 말이 있어 이동이 불가능합니다.");
        }
    }

    private boolean isDifferentTurn(Position position) {
        return find(position).map(piece -> !piece.isSameTeam(turn))
                .orElseThrow(() -> new IllegalArgumentException("해당 위치에 말이 없습니다."));
    }

    private boolean isExistSameTeam(Position position) {
        return find(position).map(piece -> piece.isSameTeam(turn))
                .orElse(false);
    }

    private boolean isExistEnemy(Position position) {
        return find(position).map(piece -> !piece.isSameTeam(turn))
                .orElse(false);
    }

    private void validateEmpty(List<Position> path) {
        if (isBlocked(path)) {
            throw new IllegalArgumentException("다른 말이 있어 이동 불가능합니다.");
        }
    }

    private boolean isBlocked(List<Position> path) {
        return path.stream()
                .limit(path.size() - 1)
                .anyMatch(this::isExistPiece);
    }

    private boolean isExistPiece(Position position) {
        return board.get(position) != null;
    }

    private boolean isNotExistPiece(Position position) {
        return !isExistPiece(position);
    }
}
