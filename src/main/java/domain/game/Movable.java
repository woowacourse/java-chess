package domain.game;

import domain.position.Position;
import java.util.Objects;

public class Movable {
    private final int maxMovement;
    private final Direction direction;

    public Movable(final int maxMovement, final Direction direction) {
        this.maxMovement = maxMovement;
        this.direction = direction;
    }

    public boolean canMove(final Position sourcePosition, final Position targetPosition) {
        Direction findDirection = Direction.findDirection(sourcePosition, targetPosition);
        if (this.direction == findDirection) {
            return doesStepExceedMaxMovement(sourcePosition, targetPosition);
        }
        return false;
    }

    private boolean doesStepExceedMaxMovement(final Position sourcePosition, final Position targetPosition) {
        int step = 0;
        Position here = new Position(sourcePosition);
        while (!here.equals(targetPosition)) {
            here = here.move(direction);
            step++;
        }
        return step <= maxMovement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Movable movable = (Movable) o;
        return direction == movable.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(direction);
    }
}
