package chessgame.domain.piecetype;

import chessgame.domain.coordinate.Coordinate;
import chessgame.domain.coordinate.Inclination;

public class BlackPawn extends Pawn {

    @Override
    public boolean isReachableByRuleWhenFirstMove(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        Inclination inclination = startCoordinate.getInclination(endCoordinate);
        return inclination.equals(Inclination.POSITIVE_INFINITY)
                && startCoordinate.hasDistanceLessThan(endCoordinate, 2);
    }

    @Override
    public boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        if (startCoordinate.equals(endCoordinate)) {
            return false;
        }

        Inclination inclination = startCoordinate.getInclination(endCoordinate);
        return inclination.equals(Inclination.POSITIVE_INFINITY)
                && startCoordinate.hasDistanceLessThan(endCoordinate, 1);
    }

    @Override
    public boolean canReap() {
        return false;
    }
}
