package domain.piece;

import java.util.Arrays;

public enum Color {

    BLACK, WHITE, NONE;

    public Color reverse() {
        return Arrays.stream(values())
                .filter(value -> value != this && value != NONE)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    public static Color fromName(String name) {
        return Arrays.stream(values())
                .filter(value -> value.name().equals(name))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

}
