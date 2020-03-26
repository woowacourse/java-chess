package chess.domains.position;

import java.util.Arrays;

public enum Direction {
    NORTH(0, 1),
    SOUTH(0, -1),
    EAST(1, 0),
    WEST(-1, 0),
    NORTHEAST(1, 1),
    SOUTHEAST(1, -1),
    SOUTHWEST(-1, -1),
    NORTHWEST(-1, 1);

    int xGap;
    int yGap;

    Direction(int xGap, int yGap) {
        this.xGap = xGap;
        this.yGap = yGap;
    }

    public static Direction findDirection(int xStep, int yStep) {
        return Arrays.stream(Direction.values())
                .filter(direction -> direction.has(xStep, yStep))
                .findFirst()
                .orElseThrow(() -> new NullPointerException("안돼요"));
    }

    private boolean has(int xGap, int yGap) {
        return this.xGap == xGap && this.yGap == yGap;
    }

    public int getxGap() {
        return xGap;
    }

    public int getyGap() {
        return yGap;
    }
}
