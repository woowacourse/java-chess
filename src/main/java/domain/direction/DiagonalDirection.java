package domain.direction;

import java.util.Arrays;

public enum DiagonalDirection implements Direction {
    UP_RIGHT(-1, 1),
    UP_LEFT(-1, -1),
    DOWN_RIGHT(1, 1),
    DOWN_LEFT(1, -1);

    private final int rowOffset;
    private final int columnOffset;

    DiagonalDirection(int rowOffset, int columnOffset) {
        this.rowOffset = rowOffset;
        this.columnOffset = columnOffset;
    }

    public static DiagonalDirection getDirection(int rowDifference, int columnDifference) {
        return Arrays.stream(values())
                .filter(direction -> direction.isSameDirection(rowDifference, columnDifference))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("이동할 수 없는 위치입니다."));
    }

    public static boolean isDiagonal(int rowDifference, int columnDifference) {
        return Math.abs(rowDifference) == Math.abs(columnDifference);
    }

    @Override
    public boolean isSameDirection(int rowDifference, int columnDifference) {
        if (Math.abs(rowDifference) != Math.abs(columnDifference)) {
            return false;
        }
        return this.rowOffset == normalize(rowDifference) && this.columnOffset == normalize(columnDifference);
    }

    @Override
    public int calculateDistance(int rowDifference, int columnDifference) {
        return (Math.abs(rowDifference) + Math.abs(columnDifference)) / 2;
    }

    @Override
    public int getRowOffset() {
        return rowOffset;
    }

    @Override
    public int getColumnOffset() {
        return columnOffset;
    }
}
