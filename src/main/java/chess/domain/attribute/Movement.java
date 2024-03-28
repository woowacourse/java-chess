package chess.domain.attribute;

public enum Movement {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    LEFT_UP(-1, 1),
    LEFT_DOWN(-1, -1),
    RIGHT_UP(1, 1),
    RIGHT_DOWN(1, -1),
    UP_UP(0, 2),
    DOWN_DOWN(0, -2),
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

    Movement(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
