package chess;

public enum Movement {
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
}
