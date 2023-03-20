package chessgame.domain.piece;

public class BlackPawn extends Pawn {

    @Override
    public boolean isReachableByRuleWhenFirstMove(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        return Double.compare(startCoordinate.getInclination(endCoordinate), Double.POSITIVE_INFINITY) == 0 &&
                startCoordinate.hasDistanceLessThan(endCoordinate, 2);
    }

    @Override
    public boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        if (startCoordinate.equals(endCoordinate)) {
            return false;
        }

        return Double.compare(startCoordinate.getInclination(endCoordinate), Double.POSITIVE_INFINITY) == 0 &&
                startCoordinate.hasDistanceLessThan(endCoordinate, 1);
    }

    @Override
    public boolean canReap() {
        return false;
    }
}
