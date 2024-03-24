package chess.domain.movement.continuous;

import chess.domain.position.Position;

public final class WestMovement extends ContinuousMovementRule {

    @Override
    protected boolean isMovable(int rankDifference, int fileDifference) {
        return rankDifference == 0 && fileDifference < 0;
    }

    @Override
    protected Position next(Position position) {
        return position.moveToWest();
    }
}
