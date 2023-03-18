package domain.piece;

import java.util.List;

public class WhitePawn extends Pawn {

    private static final List<Inclination> availableInclinations = List.of(
            Inclination.NEGATIVE_INFINITY
    );

    @Override
    public boolean isReachableByRuleWhenFirstMove(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        return availableInclinations.stream()
                .anyMatch(inclination -> inclination.isSameAs(startCoordinate.getInclination(endCoordinate))) &&
                startCoordinate.hasDistanceLessThanTwo(endCoordinate);
    }

    @Override
    public boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        return availableInclinations.stream()
                .anyMatch(inclination -> inclination.isSameAs(startCoordinate.getInclination(endCoordinate))) &&
                startCoordinate.hasDistanceLessThanOne(endCoordinate);
    }
}
