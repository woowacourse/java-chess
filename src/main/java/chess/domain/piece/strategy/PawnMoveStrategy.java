package chess.domain.piece.strategy;

import chess.domain.position.Position;
import chess.domain.position.Rank;

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
        return fromPosition.isSameRank(Rank.TWO)
            || fromPosition.isSameRank(Rank.SEVEN);
    }

    private boolean isMovableRange(Position fromPosition, Position toPosition, int range) {
        return fromPosition.isSameFile(toPosition)
            && Math.abs(fromPosition.getRankDifference(toPosition)) == range;
    }

    @Override
    public boolean isCatchable(Position fromPosition, Position toPosition) {
        return new DiagonalMoveStrategy().isMovable(fromPosition, toPosition)
            && Math.abs(fromPosition.getFileDifference(toPosition)) == RANGE
            && Math.abs(fromPosition.getRankDifference(toPosition)) == RANGE;
    }
}
