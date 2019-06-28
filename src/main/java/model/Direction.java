package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Direction {
    NORTH(0, 1),
    NORTH_EAST(1, 1),
    EAST(1, 0),
    SOUTH_EAST(1, -1),
    SOUTH(0, -1),
    SOUTH_WEST(-1, -1),
    WEST(-1, 0),
    NORTH_WEST(-1, 1);

    private static Direction[] directions = values();

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public Direction clockwiseNext() {
        return directions[(this.ordinal() + 1) % directions.length];
    }

    public Direction counterclockwiseNext() {
        return directions[(this.ordinal() + directions.length - 1) % directions.length];
    }

    public static List<Direction> ofOrthgonal() {
        return Arrays.asList(NORTH, EAST, SOUTH, WEST);
    }

    public static List<Direction> ofDiagonal() {
        return Arrays.asList(NORTH_EAST, SOUTH_EAST, SOUTH_WEST, NORTH_WEST);
    }

    public static List<Direction> ofAll() {
        List<Direction> allDirections = new ArrayList<>();

        allDirections.addAll(ofOrthgonal());
        allDirections.addAll(ofDiagonal());
        return allDirections;
    }
}
