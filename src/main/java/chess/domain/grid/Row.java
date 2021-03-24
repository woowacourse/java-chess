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

    private final char name;
    private final int index;

    Row(char name, int index) {
        this.name = name;
        this.index = index;
    }

    public static Row row(char inputName) {
        return Arrays.stream(Row.values())
                .filter(row -> row.name == inputName)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 이름의 Row가 존재하지 않습니다."));
    }

    public static Row row(int index) {
        return Arrays.stream(Row.values())
                .filter(row -> row.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 인덱스의 Row가 존재하지 않습니다."));
    }

    public char getName() {
        return name;
    }

    public int index() {
        return index;
    }
}
