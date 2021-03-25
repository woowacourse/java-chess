package chess.domain.grid;

import java.util.Arrays;

public enum Column {
    FIRST('a', 0),
    SECOND('b', 1),
    THIRD('c', 2),
    FOURTH('d', 3),
    FIFTH('e', 4),
    SIXTH('f', 5),
    SEVENTH('g', 6),
    EIGHTH('h', 7);

    private final char reference;
    private final int index;

    Column(final char reference, final int index) {
        this.reference = reference;
        this.index = index;
    }

    public static Column column(final char reference) {
        return Arrays.stream(Column.values())
                .filter(column -> column.reference == reference)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 열이 없습니다."));
    }

    public static Column column(final int index) {
        return Arrays.stream(Column.values())
                .filter(column -> column.index == index)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 열이 없습니다."));
    }

    public static boolean isValid(final char reference) {
        return Arrays.stream(Column.values())
                .anyMatch(column -> column.reference == reference);
    }

    public final Column changeColumn(final int index) {
        return Arrays.stream(Column.values())
                .filter(column -> column.index == this.index + index)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 열이 없습니다."));
    }

    public final char getReference() {
        return reference;
    }

    public final int getIndex() {
        return index;
    }

    public final int difference(final Column other) {
        return Math.abs(this.index - other.index);
    }
}
