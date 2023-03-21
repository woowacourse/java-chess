package domain.piece;

import domain.coordinate.MovePosition;
import domain.coordinate.Position;
import domain.coordinate.Route;

public final class Queen extends Piece implements VariableMover {

    public Queen(final Color color) {
        super(color, PieceType.QUEEN);
    }

    @Override
    public Route findRoute(MovePosition movePosition) {
        validateMovable(movePosition);

        int moveX = getMoveCoordinate(movePosition.diffX());
        int moveY = getMoveCoordinate(movePosition.diffY());

        return new Route(findPositions(movePosition, moveX, moveY));
    }

    @Override
    protected boolean isMovable(MovePosition movePosition) {
        int diffX = Math.abs(movePosition.diffX());
        int diffY = Math.abs(movePosition.diffY());

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
