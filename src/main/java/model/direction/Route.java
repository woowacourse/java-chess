package model.direction;

import model.position.Position;

import java.util.*;

public class Route {
    private final Direction direction;
    private final List<Position> positions;

    public Route(final Direction direction, final List<Position> positions) {
        this.direction = direction;
        this.positions = new ArrayList<>(positions);
    }

    public boolean contains(final Position position) {
        return positions.contains(position);
    }

    public void exclude(final Position position) {
        this.positions.remove(position);
    }

    public List<Position> positions() {
        return List.copyOf(positions);
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return direction == route.direction && Objects.equals(positions, route.positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(direction, positions);
    }
}
