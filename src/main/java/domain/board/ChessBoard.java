package domain.board;

import domain.piece.Empty;
import domain.piece.Piece;
import domain.position.Position;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {
    private final Map<Position, Piece> board;

    public ChessBoard(Map<Position, Piece> board) {
        this.board = new HashMap<>(board);
    }

    public void move(Position resource, Position target) {
        List<Position> positions = resource.route(target);
        if (positions.stream().anyMatch(board::containsKey)) {
            throw new IllegalArgumentException("중간에 말이 있어서 이동할 수 없습니다.");
        }
        Piece resourcePiece = findByPosition(resource);
        resourcePiece.validateMovement(resource, target, findByPosition(target));
        board.remove(resource);
        board.put(target, resourcePiece);
    }

    private Piece findByPosition(Position position) {
        return board.getOrDefault(position, Empty.getInstance());
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
