package chess.domain.piece.move;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Location {
    private final Set<Position> positions;

    public Location() {
        this.positions = new HashSet<>();
    }

    public void add(final Set<Position> positions) {
        this.positions.addAll(positions);
    }

    public boolean contains(final Position target) {
        return positions.contains(target);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Location location = (Location) o;
        return Objects.equals(positions, location.positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(positions);
    }
}
