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

    public static Direction findDirection(int xGap, int yGap) {
        if (xGap == 0 && yGap == 0) {
            throw new NullPointerException("방향을 잡지 못했어요.");
        }

        if (xGap != 0 && yGap != 0 && Math.abs(xGap) != Math.abs(yGap)) {
            throw new NullPointerException("방향을 잡지 못했어요.");
        }

        if (xGap != 0) {
            xGap /= Math.abs(xGap);
        }

        if (yGap != 0) {
            yGap /= Math.abs(yGap);
        }

        int xStep = xGap;
        int yStep = yGap;

        return Arrays.stream(Direction.values())
                .filter(direction -> direction.has(xStep, yStep))
                .findFirst()
                .orElseThrow(() -> new NullPointerException("안돼요"));
    }

    private boolean has(int xGap, int yGap) {
        return this.xGap == xGap && this.yGap == yGap;
    }
}
