package domain.movement;

import java.util.Set;

public interface MovementValidation {
    boolean isMovable(Direction direction);

    boolean isValidMoveCount(int moveCount);
}
