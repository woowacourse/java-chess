package domain.piece;

import java.util.List;

public final class BlackPawn extends Pawn {

    private static final Direction DIRECTION = new Direction(List.of(
            Inclination.NEGATIVE_INFINITY
    ));

    @Override
    public boolean isReachableByRuleWhenFirstMove(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        Inclination inclination = Inclination.of(startCoordinate.getInclination(endCoordinate));
        return DIRECTION.canBeDirectionOf(inclination) &&
                startCoordinate.hasDistanceLessThanTwo(endCoordinate);
    }

    @Override
    public boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        Inclination inclination = Inclination.of(startCoordinate.getInclination(endCoordinate));

        return DIRECTION.canBeDirectionOf(inclination) &&
                startCoordinate.hasDistanceLessThanOne(endCoordinate);
    }
}
