package chessgame.domain.piecetype;

import chessgame.domain.coordinate.Coordinate;
import chessgame.domain.coordinate.Inclination;

import java.util.List;

import static chessgame.domain.coordinate.Inclination.*;

public class King extends PieceType {

    private static final double SCORE = 0;
    private static final List<Inclination> availableInclinations = List.of(
            POSITIVE_INFINITY, NEGATIVE_INFINITY, ONE, MINUS_ONE, ZERO, MINUS_ZERO
    );

    public King() {
        super(PieceTypeSymbol.KING, SCORE);
    }

    @Override
    public boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        if (startCoordinate.equals(endCoordinate)) {
            return false;
        }
        return isMovable(startCoordinate, endCoordinate)
                && startCoordinate.hasDistanceLessThan(endCoordinate, 1);
    }

    private static boolean isMovable(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        return availableInclinations.contains(startCoordinate.getInclination(endCoordinate));
    }

    @Override
    public boolean canReap() {
        return false;
    }
}
