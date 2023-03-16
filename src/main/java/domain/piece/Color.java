package domain.piece;

import java.util.Arrays;

public enum Color {
    BLACK, WHITE;

    public Color reverse() {
        return Arrays.stream(values())
                .filter(value -> value != this)
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }

}
