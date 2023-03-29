package chess.piece;

import chess.chessboard.Position;
import chess.chessboard.Side;

public class EmptyPiece extends Piece {
    private static EmptyPiece emptyPiece;

    public EmptyPiece() {
        super(Side.EMPTY, PieceType.EMPTY);
    }

    public static EmptyPiece getInstance() {
        if (emptyPiece == null) {
            emptyPiece = new EmptyPiece();
        }
        return emptyPiece;
    }

    @Override
    public boolean isMovable(final Position source, final Position destination, final Piece piece) {
        throw new UnsupportedOperationException();
    }
}
