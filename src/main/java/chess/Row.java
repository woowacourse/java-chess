package chess;

import java.util.Arrays;

public enum Row {
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8");

    private final String name;

    Row(final String name) {
        this.name = name;
    }

    public static Row find(final String s) {
        return Arrays.stream(values())
            .filter(row -> row.name.equals(s))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("해당하는 Row가 없습니다."));
    }

    public Row move(final int step) {
        final int next = ordinal() + step;
        if (next >= 0 && next < values().length) {
            return values()[next];
        }
        return null;
    }
}
