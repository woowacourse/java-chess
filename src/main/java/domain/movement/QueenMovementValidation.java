package domain.movement;

import java.util.Set;

public class QueenMovementValidation extends AbstractMovementValidation {
    private static final Set<Direction> VALID_DIRECTIONS = Set.of(
            Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT,
            Direction.UP_LEFT, Direction.UP_RIGHT, Direction.DOWN_LEFT, Direction.DOWN_RIGHT
    );

    @Override
    public boolean isValidMoveCount(int moveCount) {
        return moveCount > 0;
    }

    @Override
    Set<Direction> validDirections() {
        return VALID_DIRECTIONS;
    }
}
