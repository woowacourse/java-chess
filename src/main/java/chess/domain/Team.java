package chess.domain;

import chess.domain.piece.Blank;

import java.util.Arrays;
import java.util.Locale;

public enum Team {
    BLACK,
    WHITE,
    NONE;

    public String getSymbol(String symbol) {
        if (this == BLACK) {
            return symbol.toUpperCase(Locale.ROOT);
        }
        if (this == WHITE) {
            return symbol.toLowerCase(Locale.ROOT);
        }
        return Blank.SYMBOL;
    }

    public boolean matchTeam(Team team) {
        return this == team;
    }

    public boolean isWhiteTeam() {
        return this == WHITE;
    }
}
