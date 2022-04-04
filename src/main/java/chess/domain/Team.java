package chess.domain;

import chess.domain.piece.Blank;

import java.util.Locale;

public enum Team {
    BLACK("Black"),
    WHITE("White"),
    NONE("None");

    private final String value;

    Team(String value) {
        this.value = value;
    }

    public String getSymbol(String symbol) {
        if (this == BLACK) {
            return symbol.toUpperCase(Locale.ROOT);
        }
        if (this == WHITE) {
            return symbol.toLowerCase(Locale.ROOT);
        }
        return Blank.SYMBOL;
    }

    public String getValue() {
        return value;
    }

    public boolean matchTeam(Team team) {
        return this == team;
    }
}
