package chess.domain.pieces;

import java.util.Arrays;

public enum Col {
    ONE("1", 7),
    TWO("2", 6),
    TREE("3", 5),
    FOUR("4", 4),
    FIVE("5", 3),
    SIX("6", 2),
    SEVEN("7", 1),
    EIGHT("8", 0);

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
