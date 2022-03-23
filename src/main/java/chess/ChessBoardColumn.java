package chess;

import java.util.Arrays;

public enum ChessBoardColumn {
    A(0),
    B(1),
    C(2),
    D(3),
    E(4),
    F(5),
    G(6),
    H(7),
    ;

    private final int value;

    ChessBoardColumn(final int value) {
        this.value = value;
    }

    public ChessBoardColumn flip() {
        return Arrays.stream(ChessBoardColumn.values())
                .filter( it -> it.value == (7 - this.value))
                .findFirst()
                .orElseThrow();
    }
}
