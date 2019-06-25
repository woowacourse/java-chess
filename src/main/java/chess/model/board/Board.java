package chess.model.board;

import chess.model.Square;
import chess.model.unit.Piece;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private Map<Square, Piece> board = new HashMap<>();

    public void initialize(BoardInitializer initializer) {
        board = initializer.initialize();
    }

    private void setPiece(Square square, Piece piece) {
        board.put(square, piece);
    }

    public Piece getPiece(Square square) {
        return board.get(square);
    }

    public boolean isNullPiece(Square square) {
        return board.get(square) == null;
    }

    public boolean isSameSide(Square beginSquare, Square endSquare) {
        if (!isNullPiece(beginSquare) && !isNullPiece(endSquare))
            return board.get(beginSquare).isSameSide(board.get(endSquare));
        return false;
    }
}