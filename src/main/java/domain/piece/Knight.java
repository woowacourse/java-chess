package domain.piece;

import java.util.List;

public class Knight implements Piece {

    private static final List<Inclination> availableInclinations = List.of(
            Inclination.ZERO_POINT_FIVE, Inclination.MINUS_ZERO_POINT_FIVE,
            Inclination.TWO, Inclination.MINUS_TWO
    );

    private static final List<Coordinate> availableCoordinateDifferences = List.of(
            new Coordinate(1, 2),
            new Coordinate(2, 1)
    );

    @Override
    public boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        return availableInclinations.stream()
                .anyMatch(inclination -> inclination.isSameAs(startCoordinate.getInclination(endCoordinate))) &&
                availableCoordinateDifferences.contains(startCoordinate.minusWithAbsoluteValue(endCoordinate));
    }

    @Override
    public boolean canReap() {
        return true;
    }
}
