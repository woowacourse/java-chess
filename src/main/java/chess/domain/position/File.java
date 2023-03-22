package chess.domain.position;

import java.util.Arrays;

public enum File {

    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8);

    private final String symbol;
    private final int index;

    File(final String symbol, final int index) {
        this.symbol = symbol;
        this.index = index;
    }

    public static File findBySymbol(final String symbol) {
        return Arrays.stream(values())
                .filter(file -> file.symbol.equals(symbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 위치입니다."));
    }

    public static File findByIndex(final int index) {
        return Arrays.stream(values())
                .filter(file -> file.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 위치입니다."));
    }

    public String symbol() {
        return this.symbol.toUpperCase();
    }
    public int index() {
        return this.index;
    }
}
