package chess.model;

import java.util.List;

public enum Name {
    PAWN("p"),
    ROOK("r"),
    KNIGHT("n"),
    BISHOP("b"),
    QUEEN("q"),
    KING("k"),
    NOTHING("."),
    ;

    private final String value;

    Name(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static List<Name> getBaseLineNames() {
        return List.of(ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK);
    }
}
