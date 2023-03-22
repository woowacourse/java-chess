package chessgame.domain.piecetype;

import chessgame.domain.coordinate.Coordinate;

public abstract class Pawn implements PieceType {

    private static final PieceTypeSymbol PIECE_TYPE_SYMBOL = PieceTypeSymbol.PAWN;

    public abstract boolean isReachableByRuleWhenFirstMove(final Coordinate startCoordinate, final Coordinate endCoordinate);

    public abstract boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate);

    @Override
    public boolean isSameTypeWith(final PieceTypeSymbol otherType) {
        return PIECE_TYPE_SYMBOL == otherType;
    }
}
