package chessgame.domain.piecetype;

import chessgame.domain.coordinate.Coordinate;
import chessgame.domain.coordinate.Inclination;

import java.util.List;

import static chessgame.domain.coordinate.Inclination.*;

public class Knight implements PieceType {

    private static final PieceTypeSymbol PIECE_TYPE_SYMBOL = PieceTypeSymbol.KNIGHT;
    private static final List<Inclination> availableInclinations = List.of(
            TWO, MINUS_TWO, HALF, MINUS_HALF
    );
    private static final List<Coordinate> availableCoordinateDifferences = List.of(
            Coordinate.fromOnBoard(1, 2),
            Coordinate.fromOnBoard(2, 1)
    );

    @Override
    public boolean isSameTypeWith(final PieceTypeSymbol otherType) {
        return PIECE_TYPE_SYMBOL == otherType;
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
