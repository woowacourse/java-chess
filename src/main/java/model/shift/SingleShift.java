package model.shift;

import model.direction.Direction;
import model.direction.ShiftPattern;
import model.position.Position;
import model.position.Route;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SingleShift implements Shift {
    private final ShiftPattern shiftPattern;

    public SingleShift(ShiftPattern shiftPattern) {
        this.shiftPattern = shiftPattern;
    }

    @Override
    public Set<Route> routes(Position position) {
        Set<Route> possibleRoutes = new HashSet<>();
        for (Direction direction : shiftPattern.getDirections()) {
            possibleRoutes.add(route(direction, position));
        }
        return possibleRoutes;
    }

    private Route route(Direction direction, Position movedPosition) {
        List<Position> sequentialPositions = new ArrayList<>();
        if (movedPosition.isAvailablePosition(direction)) {
            movedPosition = movedPosition.getNextPosition(direction);
            sequentialPositions.add(movedPosition);
        }
        return new Route(direction, sequentialPositions);
    }
}
