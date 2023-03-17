package domain.piece;

import domain.coordinate.Position;
import domain.coordinate.Route;

import java.util.List;

public final class Bishop extends Piece implements VariableMover {

    public Bishop(final Color color) {
        super(color, PieceType.BISHOP);
    }

    @Override
    public Route findRoute(final Position source, final Position target) {
        validateMovable(source, target);

        return new Route(getPositions(source, target));
    }

    @Override
    protected boolean isMovable(final Position source, final Position target) {
        return source.isDiagonally(target);
    }

    private List<Position> getPositions(final Position source, final Position target) {
        int moveX = getMoveCoordinate(target.diffX(source));
        int moveY = getMoveCoordinate(target.diffY(source));

        return findPositions(source, target, moveX, moveY);
    }

    private int getMoveCoordinate(final int diff) {
        if (diff < 0) {
            return -1;
        }

        return 1;
    }

}
