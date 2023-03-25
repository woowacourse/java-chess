package chessgame.domain.piece;

import chessgame.domain.coordinate.Coordinate;
import chessgame.domain.chessgame.Camp;

public abstract class Pawn extends Piece {

    private static final double SCORE = 1;
    private static final PieceType PIECE_TYPE_SYMBOL = PieceType.PAWN;

    public Pawn(final Camp camp) {
        super(PieceType.PAWN, camp, SCORE);
    }

    public abstract boolean isReachableByRuleWhenFirstMove(final Coordinate startCoordinate,
                                                           final Coordinate endCoordinate);

    public abstract boolean isReachableByRule(final Coordinate startCoordinate,
                                              final Coordinate endCoordinate);

    public abstract boolean isReachableWhenCatch(final Coordinate startCoordinate,
                                                 final Coordinate endCoordinate);

    @Override
    public boolean isSameTypeWith(final PieceType otherType) {
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
