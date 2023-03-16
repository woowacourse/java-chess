package chess.piece;

import java.util.Arrays;

public enum Direction {
    UP(0, 1),
    UP_RIGHT(1, 1),
    RIGHT(1, 0),
    DOWN_RIGHT(1, -1),
    DOWN(0, -1),
    DOWN_LEFT(-1, -1),
    LEFT(-1, 0),
    UP_LEFT(-1, 1),

    UP_UP_RIGHT(1, 2),
    UP_UP_LEFT(-1, 2),
    RIGHT_RIGHT_UP(2, 1),
    RIGHT_RIGHT_DOWN(2, -1),
    DOWN_DOWN_LEFT(-1, -2),
    DOWN_DOWN_RIGHT(1, -2),
    LEFT_LEFT_UP(-2, 1),
    LEFT_LEFT_DOWN(-2, -1);

    private final int x;
    private final int y;

    Direction(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public static Direction from(int xPoint, int yPoint) {
        return Arrays.stream(values())
                .filter(value -> value.x == xPoint && value.y == yPoint)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(""));
    }

    public int getNextXPoint(int xPoint) {
        return this.x + xPoint;
    }

    public int getNextYPoint(int yPoint) {
        return this.y + yPoint;
    }
}
