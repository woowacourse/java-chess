package domain.movement;

import java.util.Set;

public class KingMovementValidation implements MovementValidation {
    private static final int MOVE_COUNT = 1;
    private static final Set<Direction> DIRECTIONS = Set.of(
            Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT,
            Direction.UP_LEFT, Direction.UP_RIGHT, Direction.DOWN_LEFT, Direction.DOWN_RIGHT
    );

    @Override
    public boolean isMovable(Direction direction) {
        return DIRECTIONS.contains(direction);
    }

    @Override
    public boolean isValidMoveCount(int moveCount) {
        return moveCount == MOVE_COUNT;
    }
}
