package chess.domain.position;

import java.util.Arrays;

public enum File {

    A("a", 0),
    B("b", 1),
    C("c", 2),
    D("d", 3),
    E("e", 4),
    F("f", 5),
    G("g", 6),
    H("h", 7);

    private static final String WRONG_FILE_POSITION = "올바르지 않은 file 위치 정보입니다.";

    private final String file;
    private final int columnIndex;

    File(String file, int columnIndex) {
        this.file = file;
        this.columnIndex = columnIndex;
    }

    public static int findColumn(String row) {
        File file = Arrays.stream(values())
                .filter(value -> value.file.equals(row))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(WRONG_FILE_POSITION));
        return file.columnIndex;
    }
}
