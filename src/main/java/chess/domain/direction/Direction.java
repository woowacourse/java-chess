package chess.domain.direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Direction {

    NORTH(0, 1),
    SOUTH(0, -1),
    WEST(-1, 0),
    EAST(1, 0),
    NORTH_WEST(-1, 1),
    NORTH_EAST(1, 1),
    SOUTH_WEST(-1, -1),
    SOUTH_EAST(1, -1);

    final int row;
    final int col;

    Direction(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public static List<String> getRoute(final String start, final String end) {
        Direction direction = findDirection(start, end);
        List<String> route = new ArrayList<>();

        char row = (char) (start.charAt(1) + direction.row);
        char col = (char) (start.charAt(0) + direction.col);

        while (row != end.charAt(1) || col != end.charAt(0)) {
            route.add(col + String.valueOf(row));
            row = (char) (row + direction.row);
            col = (char) (col + direction.col);
        }
        return route;
    }

    public static Direction findDirection(final String start, final String end) {
        return Arrays.stream(Direction.values()).
                filter(direction -> direction.isSameDirection(start, end))
                .findFirst()
                .get();
    }

    private boolean isSameDirection(final String start, final String end) {
        int subRow = end.charAt(1) - start.charAt(1);
        int subCol = end.charAt(0) - start.charAt(0);
        if (subRow != 0) {
            subRow = subRow / Math.abs(subRow);
        }
        if (subCol != 0) {
            subCol = subCol / Math.abs(subCol);
        }
        return this.row == subRow && this.col == subCol;
    }
}
