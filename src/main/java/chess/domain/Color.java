package chess.domain;

import java.util.Locale;

public enum Color {
    BLACK, WHITE;

    public String correctSignature(String name) {
        if(this == BLACK) {
            return name.toUpperCase(Locale.ROOT);
        }
        return name;
    }
}
