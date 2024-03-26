package model.shift;

import model.direction.Direction;
import model.direction.Route;
import model.direction.ShiftPattern;
import model.position.Position;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class SingleShift implements Shift {
    private final ShiftPattern shiftPattern;

    public SingleShift(final ShiftPattern shiftPattern) {
        this.shiftPattern = shiftPattern;
    }

    @Override
    public Set<Route> routes(final Position position) {
        Set<Route> possibleRoutes = new HashSet<>();
        for (Direction direction : shiftPattern.getDirections()) {
            possibleRoutes.add(route(direction, position));
        }
        return possibleRoutes;
    }

    private Route route(final Direction direction, final Position position) {
        List<Position> sequentialPositions = new ArrayList<>();
        Position movedPosition = position;
        if (movedPosition.isAvailablePosition(direction)) {
            movedPosition = movedPosition.getNextPosition(direction);
            sequentialPositions.add(movedPosition);
        }
        return new Route(direction, sequentialPositions);
    }
}
