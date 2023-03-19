package chess.domain.direction;

import chess.domain.board.Position;
import chess.exception.CommandException;
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

    final int col;
    final int row;

    Direction(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public static boolean isLowerPawnDiagonal(final Position start, final Position end) {
        Direction direction = findDirection(start, end);
        return direction.equals(NORTH_WEST) || direction.equals(NORTH_EAST);
    }

    public static boolean isUpperPawnDiagonal(final Position start, final Position end) {
        Direction direction = findDirection(start, end);
        return direction.equals(SOUTH_WEST) || direction.equals(SOUTH_EAST);
    }

    public static List<String> getRoute(final Position start, final Position end) {
        Direction direction = findDirection(start, end);
        List<String> route = new ArrayList<>();

        char row = (char) (start.getRow() + direction.row);
        char col = (char) (start.getCol() + direction.col);

        while (row != end.getRow() || col != end.getCol()) {
            route.add(col + String.valueOf(row));
            row = (char) (row + direction.row);
            col = (char) (col + direction.col);
        }
        return route;
    }

    public static Direction findDirection(final Position start, final Position end) {
        return Arrays.stream(Direction.values()).
                filter(direction -> direction.isSameDirection(start, end))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(CommandException.MOVING_COMMAND_INVALID.getMessage()));
    }

    private boolean isSameDirection(final Position start, final Position end) {
        int subRow = end.getRow() - start.getRow();
        int subCol = end.getCol() - start.getCol();

        if (subRow != 0) {
            subRow = subRow / Math.abs(subRow);
        }

        if (subCol != 0) {
            subCol = subCol / Math.abs(subCol);
        }

        return this.row == subRow && this.col == subCol;
    }
}
