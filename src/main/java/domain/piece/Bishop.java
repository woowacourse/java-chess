package domain.piece;

import domain.piece.abstractpiece.SlidingPiece;
import domain.position.Position;
import domain.type.PieceType;

public final class Bishop extends SlidingPiece {

    public Bishop(final Color color) {
        super(color, PieceType.BISHOP);
    }

    @Override
    protected boolean isMovable(final Position source, final Position target) {
        return source.isDiagonally(target);
    }

    @Override
    protected int getMoveCoordinate(final int diff) {
        if (diff < 0) {
            return -1;
        }

        return 1;
    }

}
