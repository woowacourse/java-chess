package chessgame.domain.piece;

import java.util.List;

public class King implements Piece {

    private static final PieceType pieceType = PieceType.KING;
    // TODO: 매직 넘버 ENUM 지정
    private static final List<Double> availableInclinations = List.of(
            1.0, -1.0, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0.0, -0.0
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
