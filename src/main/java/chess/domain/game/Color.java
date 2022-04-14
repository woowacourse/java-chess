package chess.domain.game;

import java.util.Locale;
import java.util.Objects;

public enum Color {
    BLACK(1), WHITE(-1), NONE(0);

    private final int direction;

    Color(int direction) {
        this.direction = direction;
    }

    public static Color of(String color) {
        Objects.requireNonNull(color, "존재하지 않는 색상입니다.");

        try {
            return Color.valueOf(color);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("해당하는 색상을 찾을 수 없습니다.");
        }
    }

    public String correctSignature(String signature) {
        if (this == BLACK) {
            return signature.toUpperCase();
        }
        if (this == WHITE) {
            return signature.toLowerCase();
        }
        return signature;
    }

    public Color nextTurn() {
        if (this == BLACK) {
            return WHITE;
        }
        if (this == WHITE) {
            return BLACK;
        }
        return NONE;
    }

    public int direction() {
        return direction;
    }
}
