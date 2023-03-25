package domain.piece;

import domain.piece.abstractpiece.SlidingPiece;
import domain.position.Position;
import domain.type.PieceType;

public final class Queen extends SlidingPiece {

    public Queen(final Color color) {
        super(color, PieceType.QUEEN);
    }

    @Override
    protected boolean isMovable(final Position source, final Position target) {
        int diffX = Math.abs(target.diffX(source));
        int diffY = Math.abs(target.diffY(source));

        return isMove(diffX, diffY) && (isBishopMovable(diffX, diffY) || isRookMovable(diffX, diffY));
    }

    private static boolean isMove(final int diffX, final int diffY) {
        return diffX != 0 || diffY != 0;
    }

    private static boolean isRookMovable(final int diffX, final int diffY) {
        return (diffX == 0 || diffY == 0);
    }

    private static boolean isBishopMovable(final int diffX, final int diffY) {
        return (diffX == diffY);
    }

    @Override
    protected int getMoveCoordinate(final int diff) {
        return Integer.compare(diff, 0);
    }

}
