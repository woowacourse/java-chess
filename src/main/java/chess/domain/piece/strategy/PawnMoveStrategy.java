package chess.domain.piece.strategy;

import chess.domain.Ordinate;
import chess.domain.Position;

public class PawnMoveStrategy implements MoveStrategy {

    private static final int RANGE = 1;
    private static final int INITIAL_PAWN_MOVE_RANGE = 2;

    @Override
    public boolean isMovable(Position fromPosition, Position toPosition) {
        if (isInitialPosition(fromPosition)) {
            return isMovableRange(fromPosition, toPosition, RANGE)
                || isMovableRange(fromPosition, toPosition, INITIAL_PAWN_MOVE_RANGE);
        }
        return isMovableRange(fromPosition, toPosition, RANGE);
    }

    public boolean isInitialPosition(Position fromPosition) {
        return fromPosition.isSameOrdinate(Ordinate.TWO)
            || fromPosition.isSameOrdinate(Ordinate.SEVEN);
    }

    private boolean isMovableRange(Position fromPosition, Position toPosition, int range) {
        return fromPosition.isSameAbscissa(toPosition)
            && Math.abs(fromPosition.getOrdinateDifference(toPosition)) == range;
    }

    @Override
    public boolean isCatchable(Position fromPosition, Position toPosition) {
        return new DiagonalMoveStrategy().isMovable(fromPosition, toPosition)
            && Math.abs(fromPosition.getAbscissaDifference(toPosition)) == RANGE
            && Math.abs(fromPosition.getOrdinateDifference(toPosition)) == RANGE;
    }
}
