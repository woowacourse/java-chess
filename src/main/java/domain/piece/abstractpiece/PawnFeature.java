package domain.piece.abstractpiece;

import domain.piece.Color;
import domain.squarestatus.Piece;
import domain.type.PieceType;

public abstract class PawnFeature extends Piece {

    private static final int WHITE_DIRECTION = -1;
    private static final int BLACK_DIRECTION = 1;

    protected PawnFeature(final Color color) {
        super(color, PieceType.PAWN);
    }

    protected final boolean isPawnMovable(final int direction, final int diffY, final int diffX) {
        return diffY == direction && (WHITE_DIRECTION <= diffX && diffX <= BLACK_DIRECTION);
    }

    protected final int chooseDirection() {
        if (color.isWhite()) {
            return WHITE_DIRECTION;
        }
        return BLACK_DIRECTION;
    }

}
