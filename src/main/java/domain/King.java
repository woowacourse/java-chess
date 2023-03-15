package domain;

public class King implements NewPieceType {

    @Override
    public boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        if (startCoordinate.equals(endCoordinate)) {
            return false;
        }
        return isMovable(startCoordinate, endCoordinate) && startCoordinate.hasDistanceOfOne(endCoordinate);
    }

    private static boolean isMovable(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        return startCoordinate.hasInclinationOfOne(endCoordinate) ||
                startCoordinate.hasInclinationOfMinusOne(endCoordinate) ||
                startCoordinate.isSameRow(endCoordinate) ||
                startCoordinate.isSameCol(endCoordinate);
    }
}
