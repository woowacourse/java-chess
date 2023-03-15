package domain;

public class Bishop implements NewPieceType {

    @Override
    public boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        if (startCoordinate.equals(endCoordinate)) {
            return false;
        }
        return startCoordinate.hasInclinationOfOne(endCoordinate) ||
                startCoordinate.hasInclinationOfMinusOne(endCoordinate);
    }
}
