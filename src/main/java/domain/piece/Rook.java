package domain.piece;

import domain.piece.abstractpiece.SlidingPiece;
import domain.position.Position;
import domain.type.PieceType;

public final class Rook extends SlidingPiece {

    private static final int STAY = 0;

    public Rook(final Color color) {
        super(color, PieceType.ROOK);
    }

    @Override
    protected boolean isMovable(final Position source, final Position target) {
        return source.isStraight(target);
    }

    public int getMoveCoordinate(final int diff) {
        return Integer.compare(diff, STAY);
    }

}
