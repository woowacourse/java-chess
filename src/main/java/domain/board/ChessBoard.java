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

    public void move(Position source, Position target) {
        List<Position> positions = source.route(target);
        if (positions.stream().anyMatch(board::containsKey)) {
            throw new IllegalArgumentException("중간에 말이 있어서 이동할 수 없습니다.");
        }
        Piece sourcePiece = findByPosition(source);
        sourcePiece.validateMovement(source, target, findByPosition(target)); // todo 위로 이동
        board.remove(source);
        board.put(target, sourcePiece);
    }

    private Piece findByPosition(Position position) {
        return board.getOrDefault(position, Empty.getInstance());
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
