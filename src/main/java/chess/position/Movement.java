package chess.position;

import java.util.Arrays;
import java.util.Optional;

// 단위 이동이 정의된 이넘
public enum Movement {
    NO_MOVEMENT(0, 0),
    UP(0, 1),
    UP_UP(UP.x * 2, UP.y * 2),
    DOWN(0, -1),
    DOWN_DOWN(DOWN.x * 2, DOWN.y * 2),
    LEFT(-1, 0),
    RIGHT(1, 0),
    LEFT_UP(LEFT.x, UP.y),
    RIGHT_UP(RIGHT.x, UP.y),
    LEFT_DOWN(LEFT.x, DOWN.y),
    RIGHT_DOWN(RIGHT.x, DOWN.y),
    UP_UP_LEFT(LEFT_DOWN.x, UP_UP.y),
    UP_UP_RIGHT(RIGHT_DOWN.x, UP_UP.y),
    LEFT_LEFT_UP(LEFT.x * 2, UP.y),
    LEFT_LEFT_DOWN(LEFT.x * 2, DOWN.y),
    RIGHT_RIGHT_UP(RIGHT.x * 2, UP.y),
    RIGHT_RIGHT_DOWN(RIGHT.x * 2, DOWN.y),
    DOWN_DOWN_LEFT(LEFT_DOWN.x, DOWN_DOWN.y),
    DOWN_DOWN_RIGHT(RIGHT_DOWN.x, DOWN_DOWN.y),
    ;

    private final int x;

    private final int y;

    Movement(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public boolean isVertical() {
        return x == 0 && y != 0;
    }

    public boolean isDiagonal() {
        return x != 0 && y != 0 && Math.abs(x) == Math.abs(y);
    }

    public static Optional<Movement> find(final Offset offset) {
        return Arrays.stream(Movement.values())
                .filter(movement -> movement.x == offset.x())
                .filter(movement -> movement.y == offset.y())
                .findAny();
    }

    public static Movement findDirection(final Offset offset) {
        if (offset.x() > 0 && offset.y() == 0) {
            return RIGHT;
        } else if (offset.x() < 0 && offset.y() == 0) {
            return LEFT;
        } else if (offset.x() == 0 && offset.y() > 0) {
            return UP;
        } else if (offset.x() == 0 && offset.y() < 0) {
            return DOWN;
        } else if (offset.x() > 0 && offset.y() > 0) {
            return RIGHT_UP;
        } else if (offset.x() < 0 && offset.y() > 0) {
            return LEFT_UP;
        } else if (offset.x() > 0 && offset.y() < 0) {
            return RIGHT_DOWN;
        } else if (offset.x() < 0 && offset.y() < 0) {
            return LEFT_DOWN;
        }
        return NO_MOVEMENT;
    }
}
