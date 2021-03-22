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

    private final char name;
    private final int index;

    Column(char name, int index) {
        this.name = name;
        this.index = index;
    }

    public static Column column(char inputName) {
        return Arrays.stream(Column.values())
                .filter(column -> column.name == inputName)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 이름의 Row가 존재하지 않습니다."));
    }

    public char getName() {
        return name;
    }

    public int index() {
        return index;
    }
}
