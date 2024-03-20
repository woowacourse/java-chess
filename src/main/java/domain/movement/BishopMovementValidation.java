package domain.movement;

import java.util.Set;

public class BishopMovementValidation implements MovementValidation{
    private static final Set<Direction> DIRECTIONS = Set.of(
            Direction.UP_LEFT, Direction.UP_RIGHT, Direction.DOWN_LEFT, Direction.DOWN_RIGHT
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
