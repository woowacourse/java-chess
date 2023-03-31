package domain.piece;

public abstract class PawnFeature extends Piece {

    private static final int UNIT_MOVE_DISTANCE = 1;

    public PawnFeature(final Color color, PieceType pieceType) {
        super(color, pieceType);
    }

    protected final boolean isPawnMovable(final int direction, final int diffY, final int diffX) {
        return diffY == direction && (-UNIT_MOVE_DISTANCE <= diffX && diffX <= UNIT_MOVE_DISTANCE);
    }

    protected final int chooseDirection() {
        if (color.isWhite()) {
            return -UNIT_MOVE_DISTANCE;
        }

        return UNIT_MOVE_DISTANCE;
    }

}
