package domain.game;

import domain.position.Position;
import java.util.Objects;

public class Movable {
    private int maxStepSize;
    private final Direction direction;

    public Movable(int maxStepSize, Direction direction) {
        this.maxStepSize = maxStepSize;
        this.direction = direction;
    }

    public boolean canMove(Position sourcePosition, Position targetPosition) {
        Direction findDirection = Direction.findDirection(sourcePosition, targetPosition);
        if (this.direction == findDirection) {
            return doesStepExceedMaxStepSize(sourcePosition, targetPosition);
        }
        return false;
    }

    private boolean doesStepExceedMaxStepSize(Position sourcePosition, Position targetPosition) {
        int step = 0;
        Position here = new Position(sourcePosition);
        while (!here.equals(targetPosition)) {
            here.move(direction);
            step++;
        }
        return step <= maxStepSize;
    }

    public void decreaseMaxMovement() {
        maxStepSize--;
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
