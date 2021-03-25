package chess.domain.util;

import java.util.Arrays;

public enum RowConverter {
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

    RowConverter(final String row, final int location) {
        this.row = row;
        this.location = location;
    }

    public static int getLocation(final String row) {
        return Arrays.stream(RowConverter.values())
                .filter(value -> value.row.equals(row))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("갈 수 없는 좌표입니다."))
                .location;
    }
}
