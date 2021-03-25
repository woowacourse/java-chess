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

    Column(char reference, int index) {
        this.reference = reference;
        this.index = index;
    }

    public static Column column(char reference){
        Arrays.stream(Column.values())
                .filter(column -> column.reference == reference)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 열이 없습니다."));
    }
}
