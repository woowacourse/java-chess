package chess.domain.pieces;

import java.util.Arrays;

public enum Col {
    A("a", 0),
    B("b", 1),
    C("c", 2),
    D("d", 3),
    E("e", 4),
    F("f", 5),
    G("g", 6),
    H("h", 7);

    final String col;
    final int location;

    Col(final String col, final int location) {
        this.col = col;
        this.location = location;
    }

    public static int getLocation(final String col) {
        return Arrays.stream(Col.values())
                .filter(value -> value.col.equals(col))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .location;
    }
}
