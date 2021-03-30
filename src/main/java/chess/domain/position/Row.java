package chess.domain.position;

import chess.exception.InvalidCoordinateException;

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

    private final String row;
    private final int location;

    Row(final String row, final int location) {
        this.row = row;
        this.location = location;
    }

    public static int location(final String row) {
        return Arrays.stream(Row.values())
                .filter(value -> value.row.equals(row))
                .findFirst()
                .orElseThrow(InvalidCoordinateException::new)
                .location;
    }

    public static String initial(final int location) {
        return Arrays.stream(Row.values())
                .filter(value -> value.location == location)
                .findFirst()
                .orElseThrow(InvalidCoordinateException::new)
                .row;
    }
}
