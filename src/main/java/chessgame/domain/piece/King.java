package chessgame.domain.piece;

import java.util.List;

import static chessgame.domain.piece.Inclination.*;

public class King implements Piece {

    private static final PieceType pieceType = PieceType.KING;
    private static final List<Inclination> availableInclinations = List.of(
            POSITIVE_INFINITY, NEGATIVE_INFINITY, ONE, MINUS_ONE, ZERO, MINUS_ZERO
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
