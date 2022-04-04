package chess.domain.board;

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

    private final char value;

    Column(final char value) {
        this.value = value;
    }

    private static Column of(final int value) {
        return Arrays.stream(values())
                .filter(it -> it.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("[ERROR] 존재하지 않는 Column 입니다."));
    }

    public Column move(final int horizon) {
        return of(this.value + horizon);
    }

    public int subtractValue(final Column column) {
        return column.value - this.value;
    }

    public char getValue() {
        return value;
    }

    public String getValueToString() {
        return String.valueOf(value);
    }
}
