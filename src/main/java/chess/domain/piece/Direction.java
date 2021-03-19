package chess.domain.piece;

import chess.domain.Point;

import java.util.Arrays;

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
        int initialRowDifference = target.getRow() - source.getRow();
        int initialColumnDifference = target.getColumn() - source.getColumn();

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

    private static int normalizeDifference(int diff) {
        return Integer.compare(diff, 0);
    }

    public int getRowDirection() {
        return rowDirection;
    }

    public int getColumnDirection() {
        return columnDirection;
    }
}
