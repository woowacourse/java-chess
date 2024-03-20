package domain.movement;

import java.util.Set;

public abstract class AbstractMovementValidation implements MovementValidation {
    @Override
    public boolean isMovable(Direction direction) {
        return validDirections().contains(direction);
    }

    abstract Set<Direction> validDirections();
}
