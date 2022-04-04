package chess.domain;

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
        return symbol.toLowerCase(Locale.ROOT);
    }

    public boolean matchTeam(Team team) {
        return this == team;
    }
}
