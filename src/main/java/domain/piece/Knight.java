package domain.piece;

import java.util.List;

public final class Knight implements Piece {

    private static final Direction DIRECTION = new Direction(List.of(
            Inclination.ZERO_POINT_FIVE, Inclination.MINUS_ZERO_POINT_FIVE,
            Inclination.TWO, Inclination.MINUS_TWO
    ));

    private static final List<Coordinate> availableCoordinateDifferences = List.of(
            new Coordinate(1, 2),
            new Coordinate(2, 1)
    );

    @Override
    public boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        Inclination inclination = Inclination.of(startCoordinate.getInclination(endCoordinate));
        Coordinate coordinateDifference = startCoordinate.minusWithAbsoluteValue(endCoordinate);

        return DIRECTION.canBeDirectionOf(inclination) &&
                availableCoordinateDifferences.contains(coordinateDifference);
    }

    @Override
    public boolean canReap() {
        return true;
    }
}
