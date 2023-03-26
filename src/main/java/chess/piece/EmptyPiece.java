package chess.piece;

import chess.chessboard.Side;
import chess.chessboard.Square;

public class EmptyPiece extends Piece {
    private static EmptyPiece emptyPiece;

    public EmptyPiece() {
        super(Side.EMPTY, null);
    }

    public static EmptyPiece getInstance() {
        if (emptyPiece == null) {
            emptyPiece = new EmptyPiece();
        }
        return emptyPiece;
    }

    @Override
    public boolean isMovable(final Square source, final Square destination, final Piece piece) {
        throw new UnsupportedOperationException();
    }
}
