package chess.game;

import java.util.Arrays;

public enum Row {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private final int value;

    Row(final int value) {
        this.value = value;
    }

    public static Row of(final int value) {
        return Arrays.stream(values())
                .filter(it -> it.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 행입니다."));
    }

    public static Row of(final String value) {
        return Arrays.stream(values())
                .filter(it -> it.value == Integer.parseInt(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 행입니다."));
    }

    public int distance(final Row other) {
        return this.value - other.value;
    }

    public boolean isSeven() {
        return this == SEVEN;
    }

    public boolean isTwo() {
        return this == TWO;
    }

    public int getValue() {
        return value;
    }
}
