package domain;

import java.util.Arrays;

public enum ChessColumn {
    A(1), B(2), C(3), D(4),
    E(5), F(6), G(7), H(8);

    public final static int MAX_SIZE = values().length;
    public final static int MIN_SIZE = 1;

    private final int column;

    ChessColumn(int column) {
        this.column = column;
    }

    public static ChessColumn find(int column) {
        return Arrays.stream(values())
                .filter(chessColumn -> chessColumn.column == column)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 위치입니다."));
    }

    public static ChessColumn find(char column) {
        return Arrays.stream(values())
                .filter(chessColumn -> chessColumn.name().equalsIgnoreCase(column + ""))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 위치입니다."));
    }

    public int minus(ChessColumn other) {
        return this.column - other.column;
    }

    public int getColumn() {
        return column;
    }
}
