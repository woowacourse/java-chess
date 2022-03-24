package chess.domain.board;

import java.util.Arrays;

public enum Column {
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

    Column(final int value) {
        this.value = value;
    }

    public Column flip() {
        return Arrays.stream(Column.values())
                .filter( it -> it.value == (7 - this.value))
                .findFirst()
                .orElseThrow();
    }

    public int distance(Column otherColumn) {
        return Math.abs(this.value - otherColumn.value);
    }
}
