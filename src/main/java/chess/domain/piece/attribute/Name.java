package chess.domain.piece.attribute;

import java.util.Locale;

public enum Name {
    PAWN("P"),
    BISHOP("B"),
    KING("K"),
    QUEEN("Q"),
    KNIGHT("N"),
    ROOK("R"),
    NONE(".");
    private final String value;

    Name(String value) {
        this.value = value;
    }

    public String getValue(Team team) {
        if (team == Team.WHITE) {
            return value.toLowerCase(Locale.ROOT);
        }
        return value;
    }
}
