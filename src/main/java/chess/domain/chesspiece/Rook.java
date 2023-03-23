package chess.domain.chesspiece;

import chess.domain.Side;
import chess.domain.Square;

public class Rook extends Piece {
    private static final Rook blackRook = new Rook(Side.BLACK);
    private static final Rook whiteRook = new Rook(Side.WHITE);

    private Rook(final Side side) {
        super(side, PieceInfo.ROOK);
    }

    public static Rook of(final Side side) {
        if (side == Side.BLACK) {
            return blackRook;
        }
        return whiteRook;
    }

    @Override
    public boolean isMovable(final Square from, final Square to, final Piece piece) {
        return isNotSameSide(piece) && from.isStraight(to);
    }
}
