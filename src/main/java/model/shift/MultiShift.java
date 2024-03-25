package model.shift;

import model.direction.Direction;
import model.direction.ShiftPattern;
import model.position.Position;
import model.position.Route;

import java.util.*;

public class MultiShift implements Shift {

    private final ShiftPattern shiftPattern;

    public MultiShift(ShiftPattern shiftPattern) {
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

    private Route route(Direction direction, Position position) {
        List<Position> sequentialPositions = new ArrayList<>();
        while (position.isAvailablePosition(direction)) {
            position = position.getNextPosition(direction);
            sequentialPositions.add(position);
        }
        return new Route(direction, sequentialPositions);
    }
}
