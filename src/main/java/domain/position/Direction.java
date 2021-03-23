package domain.position;

import java.util.Arrays;
import java.util.List;

public enum Direction {

    EAST(0, 1),
    WEST(0, -1),
    SOUTH(1, 0),
    NORTH(-1, 0),
    NORTHEAST(-1, 1),
    NORTHWEST(-1, -1),
    SOUTHEAST(1, 1),
    SOUTHWEST(1, -1),
    NNE(-2, 1),
    NNW(-2, -1),
    SSE(2, 1),
    SSW(2, -1),
    EEN(-1, 2),
    EES(1, 2),
    WWN(-1, -2),
    WWS(1, -2);

    private final int rowDegree;
    private final int columnDegree;

    Direction(int rowDegree, int columnDegree) {
        this.rowDegree = rowDegree;
        this.columnDegree = columnDegree;
    }

    public static Direction linearTargetDirection(Position diff) {
        if (diff.getRowDegree() < 0) {
            return NORTH;
        }
        if (diff.getRowDegree() > 0) {
            return SOUTH;
        }
        if (diff.getColumnDegree() < 0) {
            return WEST;
        }
        return EAST;
    }

    public static Direction diagonalTargetDirection(Position diff) {
        if (diff.getRowDegree() > 0 && diff.getColumnDegree() > 0) {
            return SOUTHEAST;
        }
        if (diff.getRowDegree() > 0 && diff.getColumnDegree() < 0) {
            return SOUTHWEST;
        }
        if (diff.getRowDegree() < 0 && diff.getColumnDegree() < 0) {
            return NORTHWEST;
        }
        return NORTHEAST;
    }

    public static List<Direction> linearDirection() {
        return Arrays.asList(NORTH, EAST, SOUTH, WEST);
    }

    public static List<Direction> diagonalDirection() {
        return Arrays.asList(NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
    }

    public static List<Direction> everyDirection() {
        return Arrays.asList(NORTH, EAST, SOUTH, WEST, NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
    }

    public static List<Direction> knightDirection() {
        return Arrays.asList(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS);
    }

    public static List<Direction> whitePawnDirection() {
        return Arrays.asList(NORTH, NORTHEAST, NORTHWEST);
    }

    public static List<Direction> blackPawnDirection() {
        return Arrays.asList(SOUTH, SOUTHEAST, SOUTHWEST);
    }

    public int getRowDegree() {
        return rowDegree;
    }

    public int getColumnDegree() {
        return columnDegree;
    }

}
