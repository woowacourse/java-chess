package chess.domain.direction;

import chess.domain.Position;

import java.util.Arrays;

public enum BasicDirection implements Direction {
    NORTH(0, -1),
    WEST(-1, 0),
    EAST(1, 0),
    SOUTH(0, 1),
    NORTH_WEST(-1, -1),
    NORTH_EAST(1, -1),
    SOUTH_WEST(-1, 1),
    SOUTH_EAST(1, 1);

    private final int rowDirection;
    private final int columnDirection;

    BasicDirection(final int rowDirection, final int columnDirection) {
        this.rowDirection = rowDirection;
        this.columnDirection = columnDirection;
    }

    public static Direction from(final Position source, final Position target) {
        final int rowDiff = target.diff(source.getRow());
        final int columnDiff = target.diff(source.getColumn());
        final int greaterDiff = Math.max(Math.abs(rowDiff), Math.abs(columnDiff));
        final int rowDirection = calculateDirection(rowDiff, greaterDiff);
        final int columnDirection = calculateDirection(columnDiff, greaterDiff);

        return Arrays.stream(BasicDirection.values())
                .filter(current -> current.rowDirection == rowDirection && current.columnDirection == columnDirection)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재할 수 없는 방향입니다."));
    }

    private static int calculateDirection(final int value, final int greaterValue) {
        if (greaterValue == Math.abs(value)) {
            return value / greaterValue;
        }
        return value;
    }

    public static boolean isDiagonal(final Position source, final Position target) {
        final int rowDiff = source.diff(target.getRow());
        final int columnDiff = source.diff(target.getColumn());

        return Math.abs(rowDiff) == Math.abs(columnDiff);
    }

    @Override
    public int getRowIndex() {
        return rowDirection;
    }

    @Override
    public int getColumnIndex() {
        return columnDirection;
    }
}
