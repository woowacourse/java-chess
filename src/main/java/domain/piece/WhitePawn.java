package domain.piece;

public class WhitePawn implements Pawn {

    @Override
    public boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        if (startCoordinate.equals(endCoordinate)) {
            return false;
        }
        return Double.compare(startCoordinate.getInclination(endCoordinate), Double.NEGATIVE_INFINITY) == 0;
    }

    @Override
    public boolean canReap() {
        return false;
    }
}
