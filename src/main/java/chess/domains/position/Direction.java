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
    NORTHWEST(-1, 1),

    NORTH_NORTH_EAST(1, 2),
    NORTH_NORTH_WEST(-1, 2),
    SOUTH_SOUTH_EAST(1, -2),
    SOUTH_SOUTH_WEST(-1, -2),
    EAST_EAST_NORTH(2, 1),
    EAST_EAST_SOUTH(2, -1),
    WEST_WEST_NORTH(-2, 1),
    WEST_WEST_SOUTH(-2, -1);

    int xGap;
    int yGap;

    Direction(int xGap, int yGap) {
        this.xGap = xGap;
        this.yGap = yGap;
    }

    public static Direction findDirection(Position source, Position target) {
        int xGap = source.xGapBetween(target);
        int yGap = source.yGapBetween(target);
        return Direction.findDirection(xGap, yGap);
    }

    public static Direction findDirection(int xGap, int yGap) {
        if (xGap == 0 && yGap == 0) {
            throw new NullPointerException("방향을 잡지 못했어요.");
        }

        if (xGap == 0) {
            yGap /= Math.abs(yGap);
        }

        if (yGap == 0) {
            xGap /= Math.abs(xGap);
        }

        if (xGap != 0 && yGap != 0) {
            int step = Math.min(Math.abs(xGap), Math.abs(yGap));
            xGap /= step;
            yGap /= step;
        }

        int xStep = xGap;
        int yStep = yGap;

        return Arrays.stream(Direction.values())
                .filter(direction -> direction.has(xStep, yStep))
                .findFirst()
                .orElseThrow(() -> new NullPointerException("방향을 찾을 수 없습니다."));
    }

    public boolean isDiagonal() {
        return Arrays.asList(NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST).contains(this);
    }

    private boolean has(int xGap, int yGap) {
        return this.xGap == xGap && this.yGap == yGap;
    }

    public boolean isVertical() {
        return Arrays.asList(Direction.NORTH, Direction.SOUTH).contains(this);
    }
}
