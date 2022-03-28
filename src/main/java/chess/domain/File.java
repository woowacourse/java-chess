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

    private final int coordinate;

    File(int coordinate) {
        this.coordinate = coordinate;
    }

    public int getCoordinate() {
        return coordinate;
    }

    public static File findFile(int value) {
        return Arrays.stream(File.values())
            .filter(file -> file.coordinate == value)
            .findAny()
            .orElseThrow();
    }
}
