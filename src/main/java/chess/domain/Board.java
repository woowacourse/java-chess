package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Board {

    private final Map<Position, Piece> board;

    public Board(Map<Position, Piece> board) {
        this.board = new HashMap<>(board);
    }

    public Optional<Piece> find(Position position) {
        Piece piece = board.get(position);
        return Optional.ofNullable(piece);
    }

    public void move(Position start, Position end) {
        Piece piece = find(start)
                .orElseThrow(() -> new IllegalArgumentException("해당 위치에 말이 없습니다."));
        List<Position> path = piece.findPath(start, end);

        if (isMovable(path)) {
            throw new IllegalArgumentException("다른 말이 있어 이동 불가능합니다.");
        }

        board.remove(start);
        board.put(end, piece);
    }

    private boolean isMovable(List<Position> path) {
        return path.stream()
                .anyMatch(position -> board.get(position) != null);
    }
}
