package chess.domain;

import java.util.Arrays;

public enum Color {
    WHITE,
    BLACK,
    NONE;

    public static Color from(String colorName) {
        return Arrays.stream(values())
                .filter(color -> color.name().equals(colorName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 색입니다."));
    }

    public Color opponentColor() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }
}
