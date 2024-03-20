package domain.movement;

import java.util.Set;

public class KnightMovementValidation extends AbstractMovementValidation {
    private static final int MOVE_COUNT = 1;
    private static final Set<Direction> VALID_DIRECTIONS = Set.of(
            Direction.KNIGHT_UP_LEFT, Direction.KNIGHT_UP_RIGHT, Direction.KNIGHT_RIGHT_UP, Direction.KNIGHT_RIGHT_DOWN,
            Direction.KNIGHT_DOWN_RIGHT, Direction.KNIGHT_DOWN_LEFT, Direction.KNIGHT_LEFT_DOWN,
            Direction.KNIGHT_LEFT_UP
    );

    @Override
    public boolean isValidMoveCount(int moveCount) {
        return moveCount == MOVE_COUNT;
    }

    @Override
    Set<Direction> validDirections() {
        return VALID_DIRECTIONS;
    }
}
