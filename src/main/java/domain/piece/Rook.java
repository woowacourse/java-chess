package domain.piece;

import java.util.List;

public class Rook implements Piece {

    private static final List<Inclination> availableInclinations = List.of(
            Inclination.NEGATIVE_INFINITY, Inclination.POSITIVE_INFINITY,
            Inclination.ZERO, Inclination.MINUS_ZERO
    );

    @Override
    public boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        return availableInclinations.stream()
                .anyMatch(inclination -> inclination.isSameAs(startCoordinate.getInclination(endCoordinate)));
    }
}
