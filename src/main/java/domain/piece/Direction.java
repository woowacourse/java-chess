package domain.piece;

import java.util.List;

public final class Direction {

    private final List<Inclination> availableInclinations;

    public Direction(final List<Inclination> availableInclinations) {
        this.availableInclinations = List.copyOf(availableInclinations);
    }

    public boolean canBeDirectionOf(Inclination direction) {
        return availableInclinations.contains(direction);
    }
}
