package domain.movement;

public abstract class PawnMovementValidation extends AbstractMovementValidation {
    @Override
    public boolean isValidMoveCount(int moveCount) {
        // 1 또는 2
        // Direction에 의존
        // Position에 의존
        return moveCount == 1;
    }
}
