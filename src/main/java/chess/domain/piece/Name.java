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

    private final String name;

    Name(String name) {
        this.name = name;
    }

    public String getName(Team team) {
        if (team.isBlack()) {
            return name.toUpperCase();
        }
        return name.toLowerCase();
    }

    public static Name of(String n) {
        return Arrays.stream(Name.values())
                .filter(name -> name.name.equalsIgnoreCase(n))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());
    }
}
