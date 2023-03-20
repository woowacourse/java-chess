package chessgame.domain.piece;

import java.util.List;

import static chessgame.domain.piece.Inclination.*;

public class Rook implements Piece {

    private static final PieceType pieceType = PieceType.ROOK;
    private static final List<Inclination> availableInclinations = List.of(
            POSITIVE_INFINITY, NEGATIVE_INFINITY, ZERO, MINUS_ZERO
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
        return availableInclinations.contains(startCoordinate.getInclination(endCoordinate));
    }

    @Override
    public boolean canReap() {
        return false;
    }
}
