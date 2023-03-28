package chess.domain.side;

import java.util.Arrays;

public enum Color {
    WHITE,
    BLACK,
    NOTHING;

    public Color findOpponent() {
        if (this == Color.NOTHING) {
            return Color.NOTHING;
        }
        if (this == Color.WHITE) {
            return Color.BLACK;
        }
        return Color.WHITE;
    }

    public boolean isOpponent(final Color target) {
        if (this == WHITE) {
            return target == BLACK;
        }
        if (this == BLACK) {
            return target == WHITE;
        }
        return false;
    }

    public static Color findByName(String name) {
        Color[] colors = Color.values();
        return Arrays.stream(colors)
                .filter(colorName -> colorName.name().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("진영 없음"));
    }
}
