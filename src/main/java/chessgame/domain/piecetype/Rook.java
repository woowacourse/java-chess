package chessgame.domain.piecetype;

import java.util.List;

import static chessgame.domain.piecetype.Inclination.*;

public class Rook implements PieceType {

    private static final PieceTypeSymbol PIECE_TYPE_SYMBOL = PieceTypeSymbol.ROOK;
    private static final List<Inclination> availableInclinations = List.of(
            POSITIVE_INFINITY, NEGATIVE_INFINITY, ZERO, MINUS_ZERO
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
        return availableInclinations.contains(startCoordinate.getInclination(endCoordinate));
    }

    @Override
    public boolean canReap() {
        return false;
    }
}
