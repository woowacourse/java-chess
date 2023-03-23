package chess.domain.piece;

import java.util.Arrays;

public enum Direction {
    UP(0, 1),
    UP_RIGHT(1, 1),
    RIGHT(1, 0),
    DOWN_RIGHT(1, -1),
    DOWN(0, -1),
    DOWN_LEFT(-1, -1),
    LEFT(-1, 0),
    UP_LEFT(-1, 1);

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
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 x좌표와 y좌표에 해당하는 방향이 존재하지 않습니다."));
    }

    public int getNextXPoint(int xPoint) {
        return this.x + xPoint;
    }

    public int getNextYPoint(int yPoint) {
        return this.y + yPoint;
    }

    public boolean isHorizontalMovable() {
        return x != 0 && y == 0;
    }

    public boolean isVerticalMovable() {
        return x == 0 && y != 0;
    }

    public boolean isDiagonalMovable() {
        return x != 0 && y != 0;
    }
}
