package chess.domain;

import chess.domain.piece.Piece;

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

    public boolean matchTeam(Team team) {
        return this == team;
    }
}
