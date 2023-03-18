package domain.piece;

import java.util.List;

public class Rook implements Piece {

    private static final List<Double> availableInclinations = List.of(
            Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0.0, -0.0
    );

    @Override
    public boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        if (startCoordinate.equals(endCoordinate)) {
            return false;
        }
        return availableInclinations.contains(startCoordinate.getInclination(endCoordinate));
    }

    @Override
    public boolean canReap() {
        return false;
    }
}
