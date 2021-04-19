package chess.domain.board;

import java.util.Arrays;

public enum Column {

    A(0, 'a'),
    B(1, 'b'),
    C(2, 'c'),
    D(3, 'd'),
    E(4, 'e'),
    F(5, 'f'),
    G(6, 'g'),
    H(7, 'h');

    private final int index;
    private final char column;

    Column(int index, char column) {
        this.index = index;
        this.column = column;
    }

    public static Column findColumn(char input) {
        return Arrays.stream(values())
            .filter(value -> value.column == input)
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

    public static Column findColumnByIndex(int input) {
        return Arrays.stream(values())
            .filter(value -> value.index == input)
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

    public int getIndex() {
        return index;
    }

    public Character getString() {
        return column;
    }
}
