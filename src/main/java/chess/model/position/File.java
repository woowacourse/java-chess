package chess.model.position;

import java.util.Arrays;

public enum File {
    A(1, "a"),
    B(2, "b"),
    C(3, "c"),
    D(4, "d"),
    E(5, "e"),
    F(6, "f"),
    G(7, "g"),
    H(8, "h");

    private final int coordinate;
    private final String name;

    File(final int coordinate, final String name) {
        this.coordinate = coordinate;
        this.name = name;
    }

    public static File from(final String name) {
        return Arrays.stream(values())
                .filter(file -> file.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 file 이름입니다."));
    }

    public int minus(final File other) {
        return this.coordinate - other.coordinate;
    }

    public File findNextFile(final int offset) {
        final int nextCoordinate = offset + coordinate;
        return Arrays.stream(values())
                .filter(file -> file.coordinate == nextCoordinate)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 file 좌표입니다."));
    }
}
