package domain.direction;

import java.util.Arrays;

public enum StraightDirection implements Direction {

    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1);

    private final int rowOffset;
    private final int columnOffset;

    StraightDirection(int rowOffset, int columnOffset) {
        this.rowOffset = rowOffset;
        this.columnOffset = columnOffset;
    }

    public static StraightDirection getDirection(int rowDifference, int columnDifference) {
        return Arrays.stream(values())
                .filter(direction -> direction.isSameDirection(rowDifference, columnDifference))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("이동할 수 없는 위치입니다."));
    }

    @Override
    public boolean isSameDirection(int rowDifference, int columnDifference) {
        return this.rowOffset == normalize(rowDifference) && this.columnOffset == normalize(columnDifference);
    }

    @Override
    public int calculateDistance(int rowDifference, int columnDifference) {
        if (columnDifference == 0) {
            return Math.abs(rowDifference);
        }
        return Math.abs(columnDifference);
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
