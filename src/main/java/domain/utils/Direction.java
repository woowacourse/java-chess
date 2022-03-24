package domain.utils;

import java.util.List;
import java.util.Vector;

public enum Direction {

    EAST(1, 0),
    WEST(-1, 0),
    SOUTH(0, -1),
    NORTH(0, 1),
    NORTHEAST(1, 1),
    NORTHWEST(-1, 1),
    SOUTHEAST(1, -1),
    SOUTHWEST(-1, -1),
    EAST_NORTHEAST(2, 1),
    EAST_SOUTHEAST(2, -1),
    WEST_NORTHWEST(-2, 1),
    WEST_SOUTHWEST(-2, -1),
    NORTH_NORTHEAST(1, 2),
    NORTH_NORTHWEST(-1, 2),
    SOUTH_SOUTHEAST(1, -2),
    SOUTH_SOUTHWEST(-1, -2);

    private final int x;
    private final int y;

    Direction(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public static List<Direction> upDownLeftRightDirections() {
        return List.of(EAST, WEST, SOUTH, NORTH);
    }

    public static List<Direction> crossDirections() {
        return List.of(NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST);
    }

    public static List<Direction> allDirections() {
        return List.of(EAST, WEST, SOUTH, NORTH, NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST);
    }

    public static List<Direction> knightDirections() {
        return List.of(EAST_NORTHEAST, EAST_SOUTHEAST, WEST_NORTHWEST, WEST_SOUTHWEST, NORTH_NORTHEAST, NORTH_NORTHWEST,
                SOUTH_SOUTHEAST, SOUTH_SOUTHWEST);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
