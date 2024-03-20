package domain.movement;

public interface MovementValidation {
    boolean isMovable(Direction direction);

    boolean isValidMoveCount(int moveCount);
}
