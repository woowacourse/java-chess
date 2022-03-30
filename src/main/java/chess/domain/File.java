package chess.domain;

import java.util.Arrays;

public enum File {
    a(1),
    b(2),
    c(3),
    d(4),
    e(5),
    f(6),
    g(7),
    h(8);

    private static final String NOT_EXSIT_VALUE_IN_FILE = "[ERROR] File에 해당하는 번호가 없다.";

    private final int coordinate;

    File(int coordinate) {
        this.coordinate = coordinate;
    }

    public int getFile() {
        return coordinate;
    }

    public static File findFile(int value) {
        return Arrays.stream(File.values())
            .filter(abscissa -> abscissa.coordinate == value)
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(NOT_EXSIT_VALUE_IN_FILE));
    }
}
