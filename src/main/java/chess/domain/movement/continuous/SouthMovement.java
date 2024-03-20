package chess.domain.movement.continuous;

import chess.domain.position.Position;

public class SouthMovement extends ContinuousMovementRule {

    @Override
    protected boolean isMovable(int rankDifference, int fileDifference) {
        return fileDifference == 0 && rankDifference < 0;
    }

    @Override
    protected Position next(Position position) {
        return position.moveToSouth();
    }
}
