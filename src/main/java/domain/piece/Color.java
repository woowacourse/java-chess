package domain.piece;

import java.util.Arrays;

public enum Color {
    BLACK(true),
    WHITE(false);

    private final boolean color;

    Color(boolean color) {
        this.color = color;
    }

    public static Color of(boolean color) {
        return Arrays.stream(values())
                .filter(value -> value.color == color)
                .findAny()
                .get();
    }

    public boolean getValue() {
        return color;
    }
}
