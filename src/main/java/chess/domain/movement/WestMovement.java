package chess.domain.movement;

import chess.domain.position.Position;

public class WestMovement extends ContinuousMovementRule {

    @Override
    protected boolean isMovable(int rankDifference, int fileDifference) {
        return rankDifference == 0 && fileDifference < 0;
    }

    @Override
    protected Position next(Position position) {
        return position.moveToWest();
    }
}
