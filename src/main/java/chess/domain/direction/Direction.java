package chess.domain.direction;

import chess.domain.board.Col;
import chess.domain.board.Position;
import chess.domain.board.Row;
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

    private static final int ROW = 1;
    private static final int COLUMN = 0;

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

    public static List<Position> getRoute(final Position source, final Position destination) {
        Direction direction = findDirection(source, destination);
        List<Position> route = new ArrayList<>();

        Row row = source.nextRow(direction.row);
        Col col = source.nextCol(direction.col);

        while (!destination.isSameRow(row) || !destination.isSameColumn(col)) {
            route.add(Position.of(row, col));
            row = row.nextRow(direction.row);
            col = col.nextCol(direction.col);
        }
        return route;
    }

    public static Direction findDirection(final Position start, final Position end) {
        return Arrays.stream(Direction.values()).
            filter(direction -> direction.isSameDirection(start, end))
            .findFirst()
            .get();
    }

    private boolean isSameDirection(final Position source, final Position destination) {
        int subRow = destination.calculateDistanceOfRow(source);
        int subCol = destination.calculateDistanceOfCol(source);

        if (subRow != 0) {
            subRow = subRow / Math.abs(subRow);
        }

        if (subCol != 0) {
            subCol = subCol / Math.abs(subCol);
        }

        return this.row == subRow && this.col == subCol;
    }
}
