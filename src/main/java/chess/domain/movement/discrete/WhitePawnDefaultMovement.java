package chess.domain.movement.discrete;

public class WhitePawnDefaultMovement extends DiscreteMovementRule {

    @Override
    protected boolean isMovable(int rankDifference, int fileDifference) {
        return rankDifference == 1 && fileDifference == 0;
    }
}
