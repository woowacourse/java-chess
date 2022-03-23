package chess.domain;

import java.util.Locale;

public enum Color {
    BLACK, WHITE, NONE;

    public String correctSignature(String signature) {
        if (this == BLACK) {
            return signature.toUpperCase(Locale.ROOT);
        }
        if (this == WHITE) {
            return signature.toLowerCase(Locale.ROOT);
        }
        return signature;
    }
}
