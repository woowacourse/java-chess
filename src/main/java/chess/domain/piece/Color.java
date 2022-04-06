package chess.domain.piece;

import java.util.Arrays;

public enum Color {
    BLACK, WHITE;

    public static Color of(String colorValue) {
        return Arrays.stream(Color.values())
                .filter(color -> color.name().equals(colorValue))
                .findAny()
                .orElse(null);
    }
}
