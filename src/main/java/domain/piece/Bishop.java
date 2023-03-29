package domain.piece;

import domain.piece.abstractpiece.SlidingPiece;
import domain.position.Position;
import domain.type.PieceType;

public final class Bishop extends SlidingPiece {

    private static final int STAY = 0;
    private static final int MIN_DIFF = -1;
    private static final int MAX_DIFF = 1;

    public Bishop(final Color color) {
        super(color, PieceType.BISHOP);
    }

    @Override
    protected boolean isMovable(final Position source, final Position target) {
        return source.isDiagonally(target);
    }

    @Override
    protected int getMoveCoordinate(final int diff) {
        if (diff < STAY) {
            return MIN_DIFF;
        }

        return MAX_DIFF;
    }

}
