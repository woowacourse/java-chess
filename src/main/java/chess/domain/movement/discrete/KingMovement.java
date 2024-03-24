package chess.domain.movement.discrete;

public final class KingMovement extends DiscreteMovementRule {

    @Override
    protected boolean isMovable(int rankDifference, int fileDifference) {
        int rankDifferenceSize = Math.abs(rankDifference);
        int fileDifferenceSize = Math.abs(fileDifference);

        return rankDifferenceSize <= 1 && fileDifferenceSize <= 1;
    }
}
