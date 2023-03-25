package chessgame.domain.piecetype;

import chessgame.domain.coordinate.Coordinate;
import chessgame.domain.coordinate.Inclination;

import java.util.List;

import static chessgame.domain.coordinate.Inclination.*;

public class Knight extends PieceType {

    private static final double SCORE = 2.5;
    private static final List<Inclination> availableInclinations = List.of(
            TWO, MINUS_TWO, HALF, MINUS_HALF
    );
    private static final List<Coordinate> availableCoordinateDifferences = List.of(
            Coordinate.fromOnBoard(1, 2),
            Coordinate.fromOnBoard(2, 1)
    );

    public Knight() {
        super(PieceTypeSymbol.KNIGHT, SCORE);
    }

    @Override
    public boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        if (startCoordinate.equals(endCoordinate)) {
            return false;
        }
        return availableInclinations.contains(startCoordinate.getInclination(endCoordinate)) &&
                availableCoordinateDifferences.contains(startCoordinate.minusWithAbsoluteValue(endCoordinate));
    }

    @Override
    public boolean canReap() {
        return true;
    }
}
