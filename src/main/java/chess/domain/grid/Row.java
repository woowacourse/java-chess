package chess.domain.grid;

import java.util.Arrays;

public enum Row {
    FIRST('1', 0),
    SECOND('2', 1),
    THIRD('3', 2),
    FOURTH('4', 3),
    FIFTH('5', 4),
    SIXTH('6', 5),
    SEVENTH('7', 6),
    EIGHTH('8', 7);

    private final char reference;
    private final int index;

    Row(final char reference, final int index) {
        this.reference = reference;
        this.index = index;
    }

    public static Row row(final char reference) {
        return Arrays.stream(Row.values())
                .filter(row -> row.reference == reference)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 행이 없습니다."));
    }

    public static Row row(final int index) {
        return Arrays.stream(Row.values())
                .filter(row -> row.index + 1 == index)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 행이 없습니다."));
    }

    public final Row changeRow(final int index) {
        return Arrays.stream(Row.values())
                .filter(row -> row.index == this.index + index)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 행이 없습니다."));
    }

    public static boolean isValid(final char reference) {
        return Arrays.stream(Row.values())
                .anyMatch(row -> row.reference == reference);
    }

    public final int getIndex() {
        return index;
    }

    public final char getReference() {
        return reference;
    }

    public final int difference(final Row other) {
        return Math.abs(this.index - other.index);
    }
}
