package domain.piece.attribute.point;

public enum Movement {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    RIGHT_UP(RIGHT.x, UP.y),
    LEFT_UP(LEFT.x, UP.y),
    RIGHT_DOWN(RIGHT.x, DOWN.y),
    LEFT_DOWN(LEFT.x, DOWN.y),
    LEFT_UP_UP(LEFT.x, UP.y * 2),
    LEFT_LEFT_UP(LEFT.x * 2, UP.y),
    LEFT_DOWN_DOWN(LEFT.x, DOWN.y * 2),
    LEFT_LEFT_DOWN(LEFT.x * 2, DOWN.y),
    RIGHT_UP_UP(RIGHT.x, UP.y * 2),
    RIGHT_RIGHT_UP(RIGHT.x * 2, UP.y),
    RIGHT_DOWN_DOWN(RIGHT.x, DOWN.y * 2),
    RIGHT_RIGHT_DOWN(RIGHT.x * 2, DOWN.y);

    private final int x;
    private final int y;

    Movement(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    int x() {
        return x;
    }

    int y() {
        return y;
    }
}
