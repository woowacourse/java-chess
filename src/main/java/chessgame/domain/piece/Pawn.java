package chessgame.domain.piece;

import chessgame.domain.chessgame.Camp;
import chessgame.domain.coordinate.Coordinate;

public abstract class Pawn extends Piece {

    private static final PieceType PIECE_TYPE_SYMBOL = PieceType.PAWN;

    public Pawn(final Camp camp) {
        super(PieceType.PAWN, camp);
    }

    public abstract boolean isReachableByRule(final Coordinate startCoordinate,
                                              final Coordinate endCoordinate);

    public abstract boolean isReachableWhenCatch(final Coordinate startCoordinate,
                                                 final Coordinate endCoordinate);

    @Override
    public boolean isSameTypeWith(final PieceType otherType) {
        return PIECE_TYPE_SYMBOL == otherType;
    }

    @Override
    public boolean isCatchable(final Camp otherCamp,
                               final Coordinate startCoordinate,
                               final Coordinate endCoordinate) {
        if (isSameCamp(otherCamp)) {
            return false;
        }
        return isReachableWhenCatch(startCoordinate, endCoordinate);
    }
}
