package chess.domain;

import java.util.Arrays;

public enum File {
    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8);

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
                .filter(file -> file.coordinate == value)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NOT_EXSIT_VALUE_IN_FILE));
    }
}
