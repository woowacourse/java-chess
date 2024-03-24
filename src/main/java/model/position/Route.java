package model.position;

import model.direction.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Route {
    private final Direction direction;
    private final List<Position> positions;

    public Route(Direction direction, List<Position> positions) {
        this.direction = direction;
        this.positions = new ArrayList<>(positions);
    }

    public List<Position> getPositions() {
        return List.copyOf(positions);
    }

    public boolean contains(Position position) {
        return positions.contains(position);
    }

    public Route subRoute(Position target) {
        List<Position> subList = positions.subList(0, positions.indexOf(target));
        return new Route(direction, subList);
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
