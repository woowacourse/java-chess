package domain.piece;

import java.util.Arrays;

public enum Color {

    BLACK, WHITE, NONE;

    public Color reverse() {
        return Arrays.stream(values())
                .filter(value -> value != this && value != NONE)
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

}
