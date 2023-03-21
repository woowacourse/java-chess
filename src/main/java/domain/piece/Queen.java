package domain.piece;

import domain.coordinate.Position;
import domain.coordinate.Route;
import domain.squarestatus.Piece;
import domain.type.PieceType;

public final class Queen extends Piece {

    public Queen(final Color color) {
        super(color, PieceType.QUEEN);
    }

    @Override
    public Route findRoute(final Position source, final Position target) {
        validateMovable(source, target);

        int moveX = getMoveCoordinate(target.diffX(source));
        int moveY = getMoveCoordinate(target.diffY(source));

        return new Route(findPositions(source, target, moveX, moveY));
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

    private int getMoveCoordinate(final int diff) {
        return Integer.compare(diff, 0);
    }

}
