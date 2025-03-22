package chess;

import java.util.Arrays;

public enum Column {
    A("A"),
    B("B"),
    C("C"),
    D("D"),
    E("E"),
    F("F"),
    G("G"),
    H("H");

    private final String name;

    Column(final String name) {
        this.name = name;
    }

    public static Column find(final String s) {
        return Arrays.stream(values())
            .filter(col -> col.name.equals(s))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("해당하는 Column이 없습니다."));
    }

    public Column move(final int step) {
        final int next = ordinal() + step;
        if (next >= 0 && next < values().length) {
            return values()[next];
        }

        return null;
    }
}
