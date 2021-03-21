package chess.domain.piece;

import chess.domain.Point;

import java.util.Arrays;
import java.util.List;

import static chess.domain.piece.Color.WHITE;

public enum Direction {
    NORTH(-1, 0),
    NORTH_EAST(-1, 1),
    EAST(0, 1),
    SOUTH_EAST(1, 1),
    SOUTH(1, 0),
    SOUTH_WEST(1, -1),
    WEST(0, -1),
    NORTH_WEST(-1, -1);

    private final int rowDirection;
    private final int columnDirection;

    Direction(int rowDirection, int columnDirection) {
        this.rowDirection = rowDirection;
        this.columnDirection = columnDirection;
    }

    public static Direction findDirection(Point source, Point target) {
        int initialRowDifference = target.subtractRow(source);
        int initialColumnDifference = target.subtractColumn(source);

        if (initialRowDifference == 0 && initialColumnDifference == 0) {
            throw new IllegalArgumentException("기물이 움직이지 않습니다.");
        }

        if ((Math.abs(initialRowDifference) != Math.abs(initialColumnDifference))
                && initialRowDifference != 0 && initialColumnDifference != 0) {
            throw new IllegalArgumentException("기물이 움직일 수 없는 방향입니다.");
        }

        return Arrays.stream(Direction.values())
                .filter(direction -> direction.rowDirection == normalizeDifference(initialRowDifference)
                        && direction.columnDirection == normalizeDifference(initialColumnDifference))
                .findAny().orElseThrow(RuntimeException::new);
    }

    private static int normalizeDifference(int difference) {
        return Integer.compare(difference, 0);
    }

    public static List<Direction> pawnDirection(Color color) {
        if (color.isSameAs(WHITE)) {
            return Arrays.asList(NORTH, NORTH_EAST, NORTH_WEST);
        }
        return Arrays.asList(SOUTH, SOUTH_EAST, SOUTH_WEST);
    }

    public static List<Direction> rookDirection() {
        return Arrays.asList(NORTH, SOUTH, EAST, WEST);
    }

    public static List<Direction> bishopDirection() {
        return Arrays.asList(SOUTH_EAST, SOUTH_WEST, NORTH_EAST, NORTH_WEST);
    }

    public int addCurrentRow(int row) {
        return rowDirection + row;
    }

    public int addCurrentColumn(int column) {
        return columnDirection + column;
    }
}
