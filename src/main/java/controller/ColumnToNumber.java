package controller;

import java.util.Arrays;

public enum ColumnToNumber {

    A('a', 1),
    B('b', 2),
    C('c', 3),
    D('d', 4),
    E('e', 5),
    F('f', 6),
    G('g', 7),
    H('h', 8);

    private final char column;
    private final int number;

    ColumnToNumber(char column, int number) {
        this.column = column;
        this.number = number;
    }

    public static int of(char alphabet) {
        return Arrays.stream(values())
                .filter(columnToNumber -> columnToNumber.getColumn() == alphabet)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 열 좌표값입니다."))
                .getNumber();
    }

    private char getColumn() {
        return column;
    }

    private int getNumber() {
        return number;
    }
}
