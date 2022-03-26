package domain.position;

import java.util.Arrays;

public enum File {
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

    File(final int index, final String symbol) {
        this.index = index;
        this.symbol = symbol;
    }

    public static File of(final int index) {
        return Arrays.stream(values())
            .filter(value -> value.index == index)
            .findFirst()
            .orElse(null);
    }

    public static File of(final String symbol) {
        return Arrays.stream(values())
            .filter(value -> value.symbol.equals(symbol))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 올바르지 않은 File입니다.(a ~ h)"));
    }

    public int getIndex() {
        return index;
    }

    public static boolean isFileRange(int index) {
        return A.index <= index && index <= H.index;
    }
}
