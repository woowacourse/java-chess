package domain.piece;

import domain.coordinate.Position;
import domain.coordinate.Route;
import domain.type.PieceType;

public final class Bishop extends VariableMover {

    public Bishop(final Color color) {
        super(color, PieceType.BISHOP);
    }

    @Override
    public Route findRoute(final Position source, final Position target) {
        validateMovable(source, target);

        final Position direction = findDirection(source, target);

        return new Route(findPositions(source, target, direction));
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
