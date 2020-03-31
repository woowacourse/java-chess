package chess.domains.position;

import java.util.Arrays;

public enum Column {
    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8);

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
