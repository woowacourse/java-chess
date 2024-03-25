package model.position;

import model.direction.Direction;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Route {
    private final Direction direction;
    private final Deque<Position> positions;

    public Route(Direction direction, List<Position> positions) {
        this.direction = direction;
        this.positions = new LinkedList<>(positions);
    }

    public List<Position> getPositions() {
        return List.copyOf(positions);
    }

    public boolean contains(Position position) {
        return positions.contains(position);
    }

    public void removeSourceAndTarget() {
        if (positions.size() < 2) {
            throw new IllegalArgumentException("source위치와 target위치가 지정되어 있지 않습니다.");
        }
        positions.removeFirst();
        positions.removeLast();
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
