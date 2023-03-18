package domain.piece;

import java.util.List;

public class Bishop implements Piece {

    private static final List<Inclination> availableInclinations = List.of(
            Inclination.ONE, Inclination.MINUS_ONE
    );

    @Override
    public boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        return availableInclinations.stream()
                .anyMatch(inclination -> inclination.isSameAs(startCoordinate.getInclination(endCoordinate)));
    }
}
