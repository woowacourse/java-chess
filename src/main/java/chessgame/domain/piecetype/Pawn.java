package chessgame.domain.piecetype;

import chessgame.domain.coordinate.Coordinate;
import chessgame.domain.piece.Camp;

public abstract class Pawn extends PieceType {

    private static final double SCORE = 1;
    private static final PieceTypeSymbol PIECE_TYPE_SYMBOL = PieceTypeSymbol.PAWN;

    public Pawn(final Camp camp) {
        super(PieceTypeSymbol.PAWN, camp, SCORE);
    }

    public abstract boolean isReachableByRuleWhenFirstMove(final Coordinate startCoordinate,
                                                           final Coordinate endCoordinate);

    public abstract boolean isReachableByRule(final Coordinate startCoordinate,
                                              final Coordinate endCoordinate);

    public abstract boolean isReachableWhenCatch(final Coordinate startCoordinate,
                                                 final Coordinate endCoordinate);

    @Override
    public boolean isSameTypeWith(final PieceTypeSymbol otherType) {
        return PIECE_TYPE_SYMBOL == otherType;
    }

    @Override
    public boolean isCatchable(Camp otherCamp, Coordinate startCoordinate, Coordinate endCoordinate) {
        if (isSameCamp(otherCamp)) {
            return false;
        }
        return isReachableWhenCatch(startCoordinate, endCoordinate);
    }
}
