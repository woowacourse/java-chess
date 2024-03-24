package chess.domain.movement.discrete;

public final class KnightMovement extends DiscreteMovementRule {

    @Override
    protected boolean isMovable(int rankDifference, int fileDifference) {
        int rankDifferenceSize = Math.abs(rankDifference);
        int fileDifferenceSize = Math.abs(fileDifference);

        return (rankDifferenceSize == 2 && fileDifferenceSize == 1)
                || (fileDifferenceSize == 2 && rankDifferenceSize == 1);
    }
}
