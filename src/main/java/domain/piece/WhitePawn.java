package domain.piece;

public class WhitePawn extends Pawn {

    @Override
    public boolean isReachableByRuleWhenFirstMove(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        return Double.compare(startCoordinate.getInclination(endCoordinate), Double.NEGATIVE_INFINITY) == 0 &&
                startCoordinate.hasDistanceLessThanTwo(endCoordinate);
    }

    @Override
    public boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        if (startCoordinate.equals(endCoordinate)) {
            return false;
        }
        return Double.compare(startCoordinate.getInclination(endCoordinate), Double.NEGATIVE_INFINITY) == 0 &&
                startCoordinate.hasDistanceLessThanOne(endCoordinate);
    }

    @Override
    public boolean canReap() {
        return false;
    }
}
