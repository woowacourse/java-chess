package domain;

// TODO: WHITE PAWN, BLACK PAWN에 따라 다른 RULE(기울기)을 적용한다
public class Pawn implements NewPieceType {

    @Override
    public boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        if (startCoordinate.equals(endCoordinate)) {
            return false;
        }
        return Double.compare(startCoordinate.getInclination(endCoordinate), Double.POSITIVE_INFINITY) == 0 ||
                Double.compare(startCoordinate.getInclination(endCoordinate), Double.NEGATIVE_INFINITY) == 0;
    }
}
