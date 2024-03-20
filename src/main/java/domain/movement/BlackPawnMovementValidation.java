package domain.movement;

import java.util.Set;

public class BlackPawnMovementValidation extends PawnMovementValidation {
    private static final Set<Direction> VALID_DIRECTIONS = Set.of(
            Direction.DOWN, Direction.DOWN_LEFT, Direction.DOWN_RIGHT
    );

    @Override
    Set<Direction> validDirections() {
        return VALID_DIRECTIONS;
    }
}
