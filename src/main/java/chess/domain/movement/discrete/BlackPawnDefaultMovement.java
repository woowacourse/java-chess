package chess.domain.movement.discrete;

public final class BlackPawnDefaultMovement extends DiscreteMovementRule {

    @Override
    protected boolean isMovable(int rankDifference, int fileDifference) {
        return rankDifference == -1 && fileDifference == 0;
    }
}
