package chess.domain.movement;

import chess.domain.position.Position;

public class SouthWestMovement extends ContinuousMovementRule {

    @Override
    protected boolean isMovable(int rankDifference, int fileDifference) {
        return rankDifference < 0 && rankDifference == fileDifference;
    }

    @Override
    protected Position next(Position position) {
        return position.moveToSouth().moveToWest();
    }
}
