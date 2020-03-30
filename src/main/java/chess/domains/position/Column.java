package chess.domains.position;

import java.util.Arrays;

public enum Column {
    a(1),
    b(2),
    c(3),
    d(4),
    e(5),
    f(6),
    g(7),
    h(8);

    public static final String COLUMN_OUT_OF_RANGE_EXCEPTION_MESSAGE = "Column 범위를 벗어났습니다.";
    private int column;

    Column(int column) {
        this.column = column;
    }

    public int columnGap(Column target) {
        return target.column - this.column;
    }

    public boolean isSmallerThan(Column target) {
        return this.column < target.column;
    }

    public Column moveBy(int block) {
        return Arrays.stream(values())
                .filter(column -> column.column == this.column + block)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(COLUMN_OUT_OF_RANGE_EXCEPTION_MESSAGE));
    }

    public int getColumn() {
        return column;
    }
}
