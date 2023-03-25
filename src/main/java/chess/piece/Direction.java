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
    DOWN_DOWN_RIGHT(1, -2),
    DOWN_DOWN_LEFT(-1, -2),
    LEFT_LEFT_UP(-2, 1),
    LEFT_LEFT_DOWN(-2, -1);

    private final int x;
    private final int y;

    Direction(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public static Direction of(int xDiff, int yDiff) {
        if (xDiff == 0) {
            if (yDiff > 0) {
                return UP;
            }
            if (yDiff < 0) {
                return DOWN;
            }
        }
        if (yDiff == 0) {
            if (xDiff > 0) {
                return RIGHT;
            }
            if (xDiff < 0) {
                return LEFT;
            }
        }
        if (xDiff / yDiff == 1) {
            if (xDiff > 0) {
                return UP_RIGHT;
            }
            return DOWN_LEFT;
        }
        if (xDiff / yDiff == -1) {
            if (xDiff > 0) {
                return DOWN_RIGHT;
            }
            return UP_LEFT;
        }
        return ofKnight(xDiff, yDiff);
    }

    private static Direction ofKnight(final int xDiff, final int yDiff) {
        return Arrays.stream(values())
                .filter(direction -> direction.x == xDiff && direction.y == yDiff)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 방향입니다."));
    }

    public int getNextXPoint(int xPoint) {
        return this.x + xPoint;
    }

    public int getNextYPoint(int yPoint) {
        return this.y + yPoint;
    }

    public boolean isHorizontal() {
        return x != 0 && y == 0;
    }

    public boolean isVertical() {
        return x == 0 && y != 0;
    }

    public boolean isDiagonal() {
        return x != 0 && y != 0;
    }

    public Direction getDirectionBeforeMoveSideways() {
        if (y == 2) {
            return UP;
        }
        if (y == -2) {
            return DOWN;
        }
        if (x == 2) {
            return RIGHT;
        }
        return LEFT;
    }
}
