package chess.domain.position;

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

    Column(char value) {
        this.value = value;
    }

    public static Column find(char input) {
        return Arrays.stream(values())
                .filter(i -> i.value == input)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Column 값 입니다."));
    }

    public int getDifference(Column col) {
        return this.value - col.value;
    }

    public char getValue() {
        return value;
    }
}
