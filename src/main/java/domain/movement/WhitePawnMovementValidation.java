package domain.movement;

import java.util.Set;

public class WhitePawnMovementValidation extends PawnMovementValidation {
    private static final Set<Direction> VALID_DIRECTIONS = Set.of(
            Direction.UP, Direction.UP_LEFT, Direction.UP_RIGHT
    );

    @Override
    Set<Direction> validDirections() {
        return VALID_DIRECTIONS;
    }
}
