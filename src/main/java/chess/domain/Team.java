package chess.domain;

import chess.domain.piece.Blank;

import java.util.Arrays;
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

    public static Team find(String team) {
        return Arrays.stream(values())
                .filter(i -> i.value.equals(team))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("올바른 Team 값이 아닙니다."));
    }

    public String getValue() {
        return value;
    }

    public boolean matchTeam(Team team) {
        return this == team;
    }

    public boolean isWhiteTeam() {
        return this == WHITE;
    }
}
