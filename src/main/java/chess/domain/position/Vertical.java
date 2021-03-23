package chess.domain.position;

import java.util.Arrays;

public enum Vertical {
    ONE("1", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8);

    private final String symbol;
    private final int value;

    Vertical(final String symbol, final int value) {
        this.symbol = symbol;
        this.value = value;
    }

    public static Vertical of(final String symbol) {
        return Arrays.stream(values())
                .filter(vertical -> vertical.symbol.equals(symbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 세로 위치를 찾을 수 없습니다."));
    }

    public static Vertical of(final int value) {
        return Arrays.stream(values())
                .filter(vertical -> vertical.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 세로 위치를 찾을 수 없습니다."));
    }

    public boolean isSameValue(final Vertical vertical) {
        return value == vertical.value;
    }

    public int subtractedValue(Vertical vertical) {
        return this.value - vertical.value;
    }

    public int addedValue(int value) {
        return this.value + value;
    }

    public String symbol() {
        return symbol;
    }

    public int value() {
        return value;
    }
}
