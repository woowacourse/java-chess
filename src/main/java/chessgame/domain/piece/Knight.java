package chessgame.domain.piece;

import java.util.List;

import static chessgame.domain.piece.Inclination.*;

public class Knight implements Piece {

    private static final PieceType pieceType = PieceType.KNIGHT;
    private static final List<Inclination> availableInclinations = List.of(
            TWO, MINUS_TWO, HALF, MINUS_HALF
    );
    private static final List<Coordinate> availableCoordinateDifferences = List.of(
            new Coordinate(1, 2),
            new Coordinate(2, 1)
    );

    @Override
    public boolean isSameTypeWith(final PieceType otherType) {
        return pieceType == otherType;
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
