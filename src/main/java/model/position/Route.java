package model.position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import model.direction.Direction;

public class Route {
    private final Direction direction;
    private final List<Position> positions;

    public Route(final Direction direction, final List<Position> positions) {
        this.direction = direction;
        this.positions = new ArrayList<>(positions);
    }

    public boolean contains(Position position) {
        return positions.contains(position);
    }

    public Route directRouteTo(Position destination) {
        List<Position> subList = new ArrayList<>(positions.subList(0, positions.indexOf(destination) + 1));
        return new Route(direction, subList);
    }

    public Route reverseRouteTowardSource() {
        List<Position> reversedPositions = new ArrayList<>(positions);
        Direction oppositeDirection = direction.toOppositeDirection();
        Collections.reverse(reversedPositions);
        reversedPositions.remove(0);
        Position beforeLastPosition = positions.get(0);
        if (beforeLastPosition.hasAvailableNextPostitionToDirection(oppositeDirection)) {
            reversedPositions.add(beforeLastPosition.getNextPosition(oppositeDirection));
        }
        return new Route(oppositeDirection, reversedPositions);
    }

    public Direction getDirection() {
        return direction;
    }

    public List<Position> getPositions() {
        return List.copyOf(positions);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Route route)) {
            return false;
        }
        return direction == route.direction && Objects.equals(positions, route.positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(direction, positions);
    }
}
