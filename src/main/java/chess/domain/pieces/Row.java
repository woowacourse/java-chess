package chess.domain.pieces;

import java.util.Arrays;

public enum Row {
    A("a", 0),
    B("b", 1),
    C("c", 2),
    D("d", 3),
    E("e", 4),
    F("f", 5),
    G("g", 6),
    H("h", 7);

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
