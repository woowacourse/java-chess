package chess.domain;

import java.util.Arrays;

public enum Color {
    WHITE(0),
    BLACK(1),
    NONE(2);

    private final int value;

    Color(int value) {
        this.value = value;
    }

    public static Color from(int value) {
        return Arrays.stream(values())
                .filter(color -> color.value == value)
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
