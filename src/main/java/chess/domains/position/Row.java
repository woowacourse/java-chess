package chess.domains.position;

import java.util.Arrays;

public enum Row {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    public static final String ROW_OUT_OF_RANGE_EXCEPTION_MESSAGE = "Row의 범위를 벗어났습니다.";
    private final int row;

    Row(int row) {
        this.row = row;
    }

    public int rowGap(Row target) {
        return target.row - this.row;
    }

    public boolean isBiggerThan(Row target) {
        return this.row > target.row;
    }

    public Row moveBy(int block) {
        return Arrays.stream(values())
                .filter(row -> row.row == this.row + block)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ROW_OUT_OF_RANGE_EXCEPTION_MESSAGE));
    }

    public int getRow() {
        return row;
    }
}
