package chess.location;

import java.util.Arrays;

public enum Col {
    A('a'),
    B('b'),
    C('c'),
    D('d'),
    E('e'),
    F('f'),
    G('g'),
    H('h');

    private final char value;

    Col(char value) {
        this.value = value;
    }

    public static Col of(int value) {
        if (isInValidRange(value)) {
            throw new NoExistChessLocationException();
        }
        return Arrays.stream(Col.values())
                .filter(col -> col.value == value)
                .findAny()
                .get();
    }

    private static boolean isInValidRange(int value) {
        return A.value > value || H.value < value;
    }

    public boolean is(Col col) {
        return this.value == col.value;
    }

    public boolean isHigherThan(Col col) {
        return this.value > col.value;
    }

    public Col plus(int col) {
        return Col.of(this.value + col);
    }

    public char getValue() {
        return value;
    }
}
