package domain.move;

import domain.position.Position;
import java.util.Objects;

public class Movable {
    private final int maxMovement;
    private final Direction direction;

    public Movable(int maxMovement, Direction direction) {
        this.maxMovement = maxMovement;
        this.direction = direction;
    }

    public boolean canMove(Position sourcePosition, Position targetPosition) {
        Direction findDirection = Direction.findDirection(sourcePosition, targetPosition);
        if (this.direction == findDirection) {
            return doesStepExceedMaxMovement(sourcePosition, targetPosition);
        }
        return false;
    }

    private boolean doesStepExceedMaxMovement(Position sourcePosition, Position targetPosition) {
        int step = 0;
        Position position = sourcePosition;
        while (!position.equals(targetPosition)) {
            position = position.move(direction);
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
