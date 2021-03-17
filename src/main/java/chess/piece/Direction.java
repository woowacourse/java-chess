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
    SOUTHWEST(-1, -1),

    NNE(1,2),
    NEE(2,1),
    SEE(2,-1),
    SSE(1,-2),
    SSW(-1,-2),
    SWW(-2,-1),
    NWW(-2, 1),
    NNW(-1,2);

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

    public static List<Direction> knightDirection() {
        return Arrays.asList(NNE, NEE, SEE, SSE, SSW, SWW, NWW, NNW);
    }

    public boolean isSameDirection(int horizon, int vertical) {
        if (isOppositeDirection(horizon, vertical)) {
            return false;
        }
        return this.horizon * vertical == this.vertical * horizon;
    }

    private boolean isOppositeDirection(int horizon, int vertical) {
        return (this.horizon * horizon < 0) || (this.vertical * vertical < 0);
    }

    public int getHorizon() {
        return horizon;
    }

    public int getVertical() {
        return vertical;
    }
}
