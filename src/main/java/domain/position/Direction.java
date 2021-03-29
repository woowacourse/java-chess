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

    private final Row row;
    private final Column column;

    Direction(int rowDegree, int columnDegree) {
        this.row = new Row(rowDegree);
        this.column = new Column(columnDegree);
    }

    public static Direction linearTargetDirection(Position difference) {
        if (difference.isRowLessThanZero()) {
            return NORTH;
        }
        if (difference.isRowGreaterThanZero()) {
            return SOUTH;
        }
        if (difference.isColumnLessThanZero()) {
            return WEST;
        }
        return EAST;
    }

    public static Direction diagonalTargetDirection(Position difference) {
        if (difference.isRowGreaterThanZero() && difference.isColumnGreaterThanZero()) {
            return SOUTHEAST;
        }
        if (difference.isRowGreaterThanZero() && difference.isColumnLessThanZero()) {
            return SOUTHWEST;
        }
        if (difference.isRowLessThanZero() && difference.isColumnLessThanZero()) {
            return NORTHWEST;
        }
        return NORTHEAST;
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

    public Row rowDegreeSum(Row row) {
        return this.row.sum(row);
    }

    public Column columnDegreeSum(Column column) {
        return this.column.sum(column);
    }
}
