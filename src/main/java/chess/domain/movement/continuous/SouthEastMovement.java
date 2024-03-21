package chess.domain.movement.continuous;

import chess.domain.position.Position;

public class SouthEastMovement extends ContinuousMovementRule {

    @Override
    protected boolean isMovable(int rankDifference, int fileDifference) {
        return rankDifference < 0 && rankDifference == -fileDifference;
    }

    @Override
    protected Position next(Position position) {
        return position.moveToSouth()
                .moveToEast();
    }
}
