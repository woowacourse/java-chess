package chess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Route {

    private final List<Position> positions;

    public Route(final List<Position> positions) {
        this.positions = new ArrayList<>(positions);
    }

    public static Route from(final Position position) {
        return new Route(List.of(position));
    }

    public Route() {
        this(new ArrayList<>());
    }

    public void add(final Position position) {
        positions.add(position);
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof final Route route)) {
            return false;
        }
        return Objects.equals(getPositions(), route.getPositions());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getPositions());
    }

    public List<Position> getPositions() {
        return Collections.unmodifiableList(positions);
    }

    public Position getLast() {
        return positions.getLast();
    }
}
