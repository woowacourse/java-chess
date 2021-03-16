package chess.domain;

import java.util.Arrays;

public enum Column {
    A(1, "a"),
    B(2, "b"),
    C(3, "c"),
    D(4, "d"),
    E(5, "e"),
    F(6, "f"),
    G(7, "g"),
    H(8, "h");

    private int columnNumber;
    private String column;

    Column(int columnNumber, String column){
        this.column = column;
    }

    public static Column getColumn(int value) {
        return Arrays.stream(values())
            .filter(column -> column.columnNumber == value)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 행입니다."));
    }
}
