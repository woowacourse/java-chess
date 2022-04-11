package chess.domain.piece;

import java.util.Arrays;

public enum Color {
    BLACK, WHITE, EMPTY;

    public static Color from(final String name) {
        return Arrays.stream(values())
                .filter(color -> color.name().contains(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 색상입니다."));
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public Color next() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
