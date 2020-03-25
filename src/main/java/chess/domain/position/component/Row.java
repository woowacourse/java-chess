package chess.domain.position.component;

import java.util.Objects;

public enum Row {
    A('a'),
    B('b'),
    C('c'),
    D('d'),
    E('e'),
    F('f'),
    G('g'),
    H('h');

    private final Character value;

    Row(Character value) {
        this.value = value;
    }

    public Character getValue() {
        return value;
    }

    public static int getDiff(Row row1, Row row2) {
        Objects.requireNonNull(row1);
        Objects.requireNonNull(row2);
        return row1.getValue() - row2.getValue();
    }

    public static Row getSmaller(Row row1, Row row2) {
        if (row1.ordinal() < row2.ordinal()) {
            return row1;
        }
        return row2;
    }

    public static Row getBigger(Row row1, Row row2) {
        if (row1.ordinal() > row2.ordinal()) {
            return row1;
        }
        return row2;
    }
}
