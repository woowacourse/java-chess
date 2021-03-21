package chess.domain.position;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Horizontal {
    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8);

    private final String symbol;
    private final int value;

    Horizontal(final String symbol, final int value) {
        this.symbol = symbol;
        this.value = value;
    }

    public static Horizontal of(final String symbol) {
        return Arrays.stream(values())
            .filter(horizontal -> horizontal.symbol.equals(symbol))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("해당하는 가로 위치를 찾을 수 없습니다."));
    }

    public static Horizontal of(final int value) {
        return Arrays.stream(values())
            .filter(horizontal -> horizontal.value == value)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("해당하는 가로 위치를 찾을 수 없습니다."));
    }

    public static List<String> horizontalSymbols() {
        return Arrays.stream(values())
            .map(Horizontal::symbol)
            .collect(Collectors.toList());
    }

    public String symbol() {
        return symbol;
    }

    public int value() {
        return value;
    }

}
