package domain.piece.attribute.point;

public enum TempDirection {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    RIGHT_UP(RIGHT.x, UP.y),
    LEFT_UP(LEFT.x, UP.y),
    RIGHT_DOWN(RIGHT.x, DOWN.y),
    LEFT_DOWN(LEFT.x, DOWN.y);

    private final int x;
    private final int y;

    TempDirection(final int x, final int y) {
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
