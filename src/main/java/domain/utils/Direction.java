package domain.utils;

import java.util.List;

public enum Direction {

    EAST(1, 0),
    WEST(-1, 0),
    SOUTH(0, -1),
    NORTH(0, 1),
    NORTHEAST(1, 1),
    NORTHWEST(-1, 1),
    SOUTHEAST(1, -1),
    SOUTHWEST(-1, -1);

    private final int x;
    private final int y;

    Direction(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public static List<Direction> upDownLeftRight() {
        return List.of(EAST, WEST, SOUTH, NORTH);
    }

    public static List<Direction> crossDirection() {
        return List.of(NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
