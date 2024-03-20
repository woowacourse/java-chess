package chess.domain.movement;

import chess.domain.position.Position;

public class NorthMovement extends ContinuousMovementRule {

    @Override
    protected boolean isMovable(int rankDifference, int fileDifference) {
        return fileDifference == 0 && rankDifference > 0;
    }

    @Override
    protected Position next(Position position) {
        return position.moveToNorth();
    }
}
