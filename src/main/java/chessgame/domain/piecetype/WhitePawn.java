package chessgame.domain.piecetype;

import chessgame.domain.coordinate.Coordinate;
import chessgame.domain.coordinate.Inclination;

import java.util.List;

import static chessgame.domain.coordinate.Inclination.MINUS_ONE;
import static chessgame.domain.coordinate.Inclination.ONE;

public class WhitePawn extends Pawn {

    private static final List<Inclination> availableInclinationsWhenCatch = List.of(ONE, MINUS_ONE);

    @Override
    public boolean isReachableByRuleWhenFirstMove(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        Inclination inclination = startCoordinate.getInclination(endCoordinate);
        return inclination.equals(Inclination.NEGATIVE_INFINITY)
                && startCoordinate.hasDistanceLessThan(endCoordinate, 2);
    }

    @Override
    public boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        if (startCoordinate.equals(endCoordinate)) {
            return false;
        }

        Inclination inclination = startCoordinate.getInclination(endCoordinate);
        return inclination.equals(Inclination.NEGATIVE_INFINITY)
                && startCoordinate.hasDistanceLessThan(endCoordinate, 1);
    }

    @Override
    public boolean isReachableWhenCatch(Coordinate startCoordinate, Coordinate endCoordinate) {
        if (startCoordinate.equals(endCoordinate)) {
            return false;
        }

        Inclination inclination = startCoordinate.getInclination(endCoordinate);
        Coordinate differenceCoordinate = startCoordinate.minus(endCoordinate);
        return availableInclinationsWhenCatch.contains(inclination)
                && differenceCoordinate.hasPositiveRowValue()
                && startCoordinate.hasDistanceLessThan(endCoordinate, 1);
    }

    @Override
    public boolean canReap() {
        return false;
    }
}
