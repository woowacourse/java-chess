package chess.piece;

import java.util.Arrays;
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

    private final int horizon;
    private final int vertical;

    Direction(int horizon, int vertical) {
        this.horizon = horizon;
        this.vertical = vertical;
    }

    public static List<Direction> everyDirection() {
        return Arrays.asList(EAST, WEST, SOUTH, NORTH, NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST);
    }

    public static List<Direction> diagonalDirection() {
        return Arrays.asList(NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST);
    }

    public static List<Direction> axisDirection() {
        return Arrays.asList(EAST, WEST, SOUTH, NORTH);
    }

    public boolean isRightDirection(int horizon, int vertical) {
        return (this.horizon == horizon) && (this.vertical == vertical);
    }

    public int getHorizon() {
        return horizon;
    }

    public int getVertical() {
        return vertical;
    }
}
