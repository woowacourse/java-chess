package chess.domain.chesspiece;

import chess.domain.Side;
import chess.domain.Square;

public class EmptyPiece extends Piece {
    private static EmptyPiece emptyPiece;

    private EmptyPiece() {
        super(Side.NO_SIDE, PieceInfo.EMPTY_PIECE);
    }

    public static EmptyPiece getInstance() {
        if (emptyPiece == null) {
            emptyPiece = new EmptyPiece();
        }
        return emptyPiece;
    }

    @Override
    public boolean isMovable(final Square from, final Square to, final Piece piece) {
        throw new UnsupportedOperationException();
    }
}
