package domain.piece;

import java.util.List;

public final class Bishop implements Piece {

    private static final Direction DIRECTION = new Direction(List.of(
            Inclination.ONE, Inclination.MINUS_ONE
    ));

    @Override
    public boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        Inclination inclination = Inclination.of(startCoordinate.getInclination(endCoordinate));

        return DIRECTION.canBeDirectionOf(inclination);
    }
}
