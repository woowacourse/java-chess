package chess.domains.position;

import java.util.Arrays;

public enum Column {
    A('a'),
    B('b'),
    C('c'),
    D('d'),
    E('e'),
    F('f'),
    G('g'),
    H('h');

    public static final String COLUMN_OUT_OF_RANGE_EXCEPTION_MESSAGE = "Column 범위를 벗어났습니다.";

    private char column;

    Column(char column) {
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

    public char getColumn() {
        return this.column;
    }
}
