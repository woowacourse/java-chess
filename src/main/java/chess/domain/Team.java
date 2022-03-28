package chess.domain;

import java.util.Locale;

public enum Team {
    BLACK,
    WHITE,
    NONE;

    public String getSymbol(String symbol) {
        if (this == BLACK) {
            return symbol.toUpperCase(Locale.ROOT);
        }
        return symbol.toLowerCase(Locale.ROOT);
    }
}
