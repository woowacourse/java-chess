package chess;

import java.util.Arrays;

public enum ChessBoardRow {
    ONE(0),
    TWO(1),
    THREE(2),
    FOUR(3),
    FIVE(4),
    SIX(5),
    SEVEN(6),
    EIGHT(7),
    ;

    private final int value;

    ChessBoardRow(int value) {
        this.value = value;
    }

    public ChessBoardRow flip() {
        return Arrays.stream(ChessBoardRow.values())
                .filter( it -> it.value == (7 - this.value))
                .findFirst()
                .orElseThrow();
    }
}
