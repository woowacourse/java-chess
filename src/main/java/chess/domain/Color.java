package chess.domain;

import java.util.Arrays;

public enum Color {
    WHITE,
    BLACK;

    public static Color changeColor(final Color current) {
        return Arrays.stream(Color.values())
                .filter(color -> color != current)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("색상을 확인해 주세요."));
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

}
