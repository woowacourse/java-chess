package chess.domain;

import java.util.Arrays;

public enum Color {
    WHITE,
    BLACK,
    NONE,
    ;

    public static Color of(String name) {
        String upperName = name.toUpperCase();
        return Arrays.stream(values())
            .filter(value -> value.name().equals(upperName))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당하는 색깔이 없습니다."));
    }

    public Color toggle() {
        validateColor();
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }

    private void validateColor() {
        if (this == NONE) {
            throw new IllegalArgumentException("[ERROR] None color can't be toggled");
        }
    }
}
