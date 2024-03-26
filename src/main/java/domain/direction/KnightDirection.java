package domain.direction;

import java.util.Arrays;

public enum KnightDirection implements Direction {
    UP_RIGHT(-2, 1),
    UP_LEFT(-2, -1),
    DOWN_RIGHT(2, 1),
    DOWN_LEFT(2, -1);

    private final int rowOffset;
    private final int columnOffset;

    KnightDirection(int rowOffset, int columnOffset) {
        this.rowOffset = rowOffset;
        this.columnOffset = columnOffset;
    }

    public static KnightDirection getDirection(int rowDifference, int columnDifference) {
        return Arrays.stream(values())
                .filter(direction -> direction.isSameDirection(rowDifference, columnDifference))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("이동할 수 없는 위치입니다."));
    }

    @Override
    public boolean isSameDirection(int rowDifference, int columnDifference) {
        return this.rowOffset == rowDifference && this.columnOffset == columnDifference;
    }

    @Override
    public int calculateDistance(int rowDifference, int columnDifference) {
        return 1;
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
