package domain.piecetype;

import java.util.List;

public class King implements PieceType {

    // TODO: 매직 넘버 ENUM 지정
    private static final List<Double> availableInclinations = List.of(
            1.0, -1.0, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0.0, -0.0
    );

    @Override
    public boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        if (startCoordinate.equals(endCoordinate)) {
            return false;
        }
        return isMovable(startCoordinate, endCoordinate) && startCoordinate.hasDistanceOfOne(endCoordinate);
    }

    private static boolean isMovable(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        System.out.println(startCoordinate.getInclination(endCoordinate));
        return availableInclinations.contains(startCoordinate.getInclination(endCoordinate));
    }

    @Override
    public boolean canReap() {
        return false;
    }
}
