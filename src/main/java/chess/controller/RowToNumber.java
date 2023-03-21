package chess.controller;

import java.util.Arrays;

public enum RowToNumber {

    A('1', 1),
    B('2', 2),
    C('3', 3),
    D('4', 4),
    E('5', 5),
    F('6', 6),
    G('7', 7),
    H('8', 8);

    private final char row;
    private final int number;

    RowToNumber(char row, int number) {
        this.row = row;
        this.number = number;
    }

    public static int of(char alphabet) {
        return Arrays.stream(values())
                .filter(rowToNumber -> rowToNumber.getRow() == alphabet)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 행 좌표값입니다."))
                .getNumber();
    }

    private char getRow() {
        return row;
    }

    private int getNumber() {
        return number;
    }
}
