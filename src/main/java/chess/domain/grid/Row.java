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

    Row(char reference, int index) {
        this.reference = reference;
        this.index = index;
    }

    public static Row row(int index){
        Arrays.stream(Row.values())
                .filter(row -> row.index == index)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 행이 없습니다."));
    }
}
