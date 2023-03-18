package domain.piece;

import java.util.List;

public class King implements Piece {

    private static final List<Inclination> availableInclinations = List.of(
            Inclination.ONE, Inclination.MINUS_ONE, Inclination.NEGATIVE_INFINITY, Inclination.POSITIVE_INFINITY,
            Inclination.ZERO, Inclination.MINUS_ZERO
    );

    @Override
    public boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        return availableInclinations.stream()
                .anyMatch(inclination -> inclination.isSameAs(startCoordinate.getInclination(endCoordinate))) &&
                startCoordinate.hasDistanceLessThanOne(endCoordinate);
    }
}
