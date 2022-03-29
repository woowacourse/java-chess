package chess.domain;

import java.util.Locale;

public enum Color {
    BLACK(1), WHITE(-1), NONE(0);

    private final int direction;

    Color(int direction) {
        this.direction = direction;
    }

    public String correctSignature(String signature) {
        if (this == BLACK) {
            return signature.toUpperCase(Locale.ROOT);
        }
        if (this == WHITE) {
            return signature.toLowerCase(Locale.ROOT);
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
