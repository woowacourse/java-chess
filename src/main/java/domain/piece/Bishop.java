package domain.piece;

import domain.coordinate.Position;
import domain.coordinate.Route;

import java.util.ArrayList;
import java.util.List;

public final class Bishop extends Piece {

    public Bishop(final Color color) {
        super(color);
    }

    @Override
    public Route findRoute(final Position source, final Position target) {
        validateMovable(source, target);

        return new Route(getPositions(source, target));
    }

    @Override
    protected boolean isMovable(final Position source, final Position target) {
        final int diffX = Math.abs(source.diffX(target));
        final int diffY = Math.abs(source.diffY(target));

        return (diffX != 0 || diffY != 0) && (diffX == diffY);
    }

    private List<Position> findPositions(final Position source, final Position target, final int moveX, final int moveY) {
        List<Position> positions = new ArrayList<>();
        Position position = source.move(moveX, moveY);

        while (position != target) {
            positions.add(position);
            position = position.move(moveX, moveY);
        }
        return positions;
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
