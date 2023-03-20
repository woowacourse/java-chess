package domain.piece;

public abstract class PawnFeature extends Piece {

    public PawnFeature(final Color color) {
        super(color, PieceType.PAWN);
    }

    protected final boolean isPawnMovable(final int direction, final int diffY, final int diffX) {
        return diffY == direction && (-1 <= diffX && diffX <= 1);
    }

    protected final int chooseDirection() {
        if (color.isWhite()) {
            return -1;
        }

        return 1;
    }

}
