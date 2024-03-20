package domain.movement;

import java.util.Set;

public class RookMovementValidation implements MovementValidation{
    private static final Set<Direction> DIRECTIONS = Set.of(
            Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT
    );

    @Override
    public boolean isMovable(Direction direction) {
        return DIRECTIONS.contains(direction);
    }

    @Override
    public boolean isValidMoveCount(int moveCount) {
        return moveCount > 0;
    }
}
