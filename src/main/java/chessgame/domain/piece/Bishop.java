package chessgame.domain.piece;

import java.util.List;

public class Bishop implements Piece {

    private static final PieceType pieceType = PieceType.BISHOP;
    private static final List<Double> availableInclinations = List.of(1.0, -1.0);

    @Override
    public boolean isSameTypeWith(final PieceType otherType) {
        return pieceType == otherType;
    }

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
