package chessgame.domain.piece;

import java.util.List;

import static chessgame.domain.piece.Inclination.MINUS_ONE;
import static chessgame.domain.piece.Inclination.ONE;

public class Bishop implements Piece {

    private static final PieceType pieceType = PieceType.BISHOP;
    private static final List<Inclination> availableInclinations = List.of(ONE, MINUS_ONE);

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
