package domain;

import domain.piece.Piece;
import domain.piece.Position;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private static final int SIZE = 8;
    private Map<Position, Piece> board;

    public Board(Map<Position, Piece> pieces) {
        board = new HashMap<>(pieces);
    }

    public void move(Position start, Position end) {
        Piece startPiece = board.get(start);
        if (startPiece.canMove2(start, end)) {
            board.remove(start);
            board.put(end, startPiece);
        }
    }

    public Piece getPiece(Position position) {
        return board.get(position);
    }

    public boolean canMovable(Position position, boolean color) {
        return board.get(position).isSameColor(color);
    }
}
