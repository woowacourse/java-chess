package chess.domain.piece;

import chess.domain.Point;

import java.util.Arrays;

public enum Direction {
    NORTH(-1,0),
    NORTH_EAST(-1,1),
    EAST(0,1),
    SOUTH_EAST(1,1),
    SOUTH(1,0),
    SOUTH_WEST(1,-1),
    WEST(0,-1),
    NORTH_WEST(-1,-1);

    private final int row_direction;
    private final int column_direction;

    Direction(int row_direction, int column_direction) {
        this.row_direction = row_direction;
        this.column_direction = column_direction;
    }

    public static Direction findDirection(Point source, Point target) {
        int rowDifference = normalizeDifference(target.getRow()-source.getRow());
        int columnDifference = normalizeDifference(target.getColumn()-source.getColumn());

        return Arrays.stream(Direction.values())
                .filter(direction -> direction.row_direction == rowDifference && direction.column_direction == columnDifference)
                .findAny().orElseThrow(RuntimeException::new);
    }

    private static int normalizeDifference(int diff) {
        return Integer.compare(diff, 0);
    }
}
