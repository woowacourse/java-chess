package domain.position;

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

    private final int index;
    private final String symbol;

    Column(final int index, final String symbol) {
        this.index = index;
        this.symbol = symbol;
    }

    public static Column of(final int index) {
        return Arrays.stream(values())
            .filter(value -> value.index == index)
            .findFirst()
            .orElse(null);
    }

    public int getIndex() {
        return index;
    }

//    public Column move(int moveIndex) {
//        return indexOf(moveIndex);
//    }

    public static boolean isColumnRange(int index) {
        return A.index <= index && index <= H.index;
    }
}
