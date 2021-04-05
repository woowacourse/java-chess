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

    private static final String COLUMN_INDEX_ERROR = "범위를 벗어난 column 입니다.";

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
                .orElseThrow(() -> new IllegalArgumentException(COLUMN_INDEX_ERROR));
    }

    public static Column findColumnByIndex(int input) {
        return Arrays.stream(values())
                .filter(value -> value.index == input)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(COLUMN_INDEX_ERROR));
    }

    public static boolean isInBound(int index) {
        return Arrays.stream(values()).anyMatch(value -> value.index == index);
    }

    public int getIndex() {
        return index;
    }

    public char getColumn() {
        return column;
    }
}
