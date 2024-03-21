package model.position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Route {
    private final List<Position> positions;

    public Route(final List<Position> positions) {
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
        return new Route(subList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Route route)) {
            return false;
        }
        return Objects.equals(positions, route.positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(positions);
    }
}
