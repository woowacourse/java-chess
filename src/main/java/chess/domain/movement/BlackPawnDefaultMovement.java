package chess.domain.movement;

import chess.domain.movement.discrete.DiscreteMovementRule;

public class BlackPawnDefaultMovement extends DiscreteMovementRule {

    @Override
    protected boolean isMovable(int rankDifference, int fileDifference) {
        return rankDifference == -1 && fileDifference == 0;
    }
}
