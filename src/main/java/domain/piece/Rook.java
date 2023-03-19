package domain.piece;

import java.util.List;

public final class Rook implements Piece {

    private static final Direction DIRECTION = new Direction(List.of(
            Inclination.NEGATIVE_INFINITY, Inclination.POSITIVE_INFINITY,
            Inclination.ZERO, Inclination.MINUS_ZERO
    ));

    @Override
    public boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        Inclination inclination = Inclination.of(startCoordinate.getInclination(endCoordinate));

        return DIRECTION.canBeDirectionOf(inclination);
    }
}
