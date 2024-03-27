package domain.game;

import domain.position.Position;
import java.util.Objects;

public class Movable {
    private final int maxMovement;
    private final Direction direction;

    public Movable(final int maxMovement, final Direction direction) {
        validateMaxMovement(maxMovement);
        this.maxMovement = maxMovement;
        this.direction = direction;
    }

    private void validateMaxMovement(final int maxMovement) {
        if (maxMovement <= 0) {
            throw new IllegalArgumentException("[ERROR] 최대 이동 횟수는 1 이상이어야 합니다.");
        }
    }

    public boolean canMove(final Position source, final Position target) {
        Direction findDirection = Direction.findDirection(source, target);
        if (this.direction == findDirection) {
            return doesStepExceedMaxMovement(source, target);
        }
        return false;
    }

    private boolean doesStepExceedMaxMovement(final Position source, final Position target) {
        int step = 0;
        Position here = new Position(source);
        while (!here.equals(target)) {
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
