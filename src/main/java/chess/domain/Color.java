package chess.domain;

import java.util.Locale;

public enum Color {
    BLACK, WHITE, NONE;

    public String correctSignature(String signature) {
        if (this == BLACK) {
            return signature.toUpperCase(Locale.ROOT);
        }
        return signature;
    }
}
