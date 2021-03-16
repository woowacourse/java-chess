package chess.domain.board;

import java.util.Arrays;

public enum Horizontal {
    A("a"),
    B("b"),
    C("c"),
    D("d"),
    E("e"),
    F("f"),
    G("g"),
    H("h");

    private final String value;

    Horizontal(final String horizontalValue) {
        this.value = horizontalValue;
    }

    public static Horizontal find(final String value) {
        return Arrays.stream(Horizontal.values())
                .filter(horizontal -> horizontal.value.equals(value))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
