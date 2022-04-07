package chess.domain.piece;

import java.util.Arrays;

public enum Name {
    PAWN("p"),
    ROOK("r"),
    KNIGHT("n"),
    BISHOP("b"),
    QUEEN("q"),
    KING("k"),
    NONE(".");

    private final String value;

    Name(String value) {
        this.value = value;
    }

    public String getName(Team team) {
        if (team.isBlack()) {
            return value.toUpperCase();
        }
        return value.toLowerCase();
    }

    public static Name of(String value) {
        return Arrays.stream(Name.values())
                .filter(name -> name.value.equalsIgnoreCase(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("없는 기물입니다."));
    }
}
