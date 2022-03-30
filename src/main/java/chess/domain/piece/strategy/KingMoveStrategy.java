package chess.domain.piece.strategy;

import chess.domain.position.Position;

public class KingMoveStrategy implements MoveStrategy {

    private static final int RANGE = 1;

    @Override
    public boolean isMovable(Position fromPosition, Position toPosition) {
        return isMovableDirection(fromPosition, toPosition) && isMovableRange(fromPosition, toPosition);
    }

    private boolean isMovableDirection(Position fromPosition, Position toPosition) {
        return new CrossMoveStrategy().isMovable(fromPosition, toPosition)
            || new DiagonalMoveStrategy().isMovable(fromPosition, toPosition);
    }

    private boolean isMovableRange(Position fromPosition, Position toPosition) {
        return Math.abs(fromPosition.getFileDifference(toPosition)) == RANGE
            || Math.abs(fromPosition.getRankDifference(toPosition)) == RANGE;
    }

    @Override
    public boolean isCatchable(Position fromPosition, Position toPosition) {
        return isMovable(fromPosition, toPosition);
    }
}
