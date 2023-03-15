package domain;

public class Rook implements NewPieceType {

    @Override
    public boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        if (startCoordinate.equals(endCoordinate)) {
            return false;
        }
        return startCoordinate.isSameRow(endCoordinate) ||
                startCoordinate.isSameCol(endCoordinate);
    }
}
