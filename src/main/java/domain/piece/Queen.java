package domain.piece;

import java.util.List;

public class Queen implements Piece {

    private static final List<Double> availableInclinations = List.of(
            1.0, -1.0, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0.0, -0.0
    );

    @Override
    public boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        if (startCoordinate.equals(endCoordinate)) {
            return false;
        }
        return availableInclinations.contains(startCoordinate.getInclination(endCoordinate));
    }
}
