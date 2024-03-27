package chessgame.domain.piece.attribute.point;

public enum Movement {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    RIGHT_UP(RIGHT.x, UP.y),
    LEFT_UP(LEFT.x, UP.y),
    RIGHT_DOWN(RIGHT.x, DOWN.y),
    LEFT_DOWN(LEFT.x, DOWN.y),
    UP_UP(0, UP.y * 2),
    DOWN_DOWN(0, DOWN.y * 2),
    LEFT_UP_UP(LEFT.x, UP_UP.y),
    RIGHT_UP_UP(RIGHT.x, UP_UP.y),
    LEFT_DOWN_DOWN(LEFT.x, DOWN_DOWN.y),
    RIGHT_DOWN_DOWN(RIGHT.x, DOWN_DOWN.y),
    LEFT_LEFT_UP(LEFT.x * 2, UP.y),
    LEFT_LEFT_DOWN(LEFT.x * 2, DOWN.y),
    RIGHT_RIGHT_UP(RIGHT.x * 2, UP.y),
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
