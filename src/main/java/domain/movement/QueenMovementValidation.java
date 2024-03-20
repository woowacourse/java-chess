package domain.movement;

import java.util.Set;

public class QueenMovementValidation {
    private static final Set<Direction> DIRECTIONS = Set.of(
            Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT,
            Direction.UP_LEFT, Direction.UP_RIGHT, Direction.DOWN_LEFT, Direction.DOWN_RIGHT
    );

    public boolean isMovable(Direction direction) {
        return DIRECTIONS.contains(direction);
    }

    public boolean isValidMoveCount(int moveCount) {
        return moveCount > 0;
    }
}
