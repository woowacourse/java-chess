package chess.domain.pieces;

import java.util.Arrays;

public enum Row {
    ONE("1", 7),
    TWO("2", 6),
    TREE("3", 5),
    FOUR("4", 4),
    FIVE("5", 3),
    SIX("6", 2),
    SEVEN("7", 1),
    EIGHT("8", 0);

    final String row;
    final int location;

    Row(final String row, final int location) {
        this.row = row;
        this.location = location;
    }

    public static int getLocation(final String row) {
        return Arrays.stream(Row.values())
                .filter(value -> value.row.equals(row))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .location;
    }
}
