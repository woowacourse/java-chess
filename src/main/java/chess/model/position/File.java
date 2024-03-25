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

    File(int coordinate, String name) {
        this.coordinate = coordinate;
        this.name = name;
    }

    public static File from(String name) {
        return Arrays.stream(values())
                .filter(file -> file.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 File 이름입니다."));
    }

    public Difference minus(File other) {
        return Difference.from(this.coordinate - other.coordinate);
    }

    public File calculateNextFile(int offset) {
        return Arrays.stream(values())
                .filter(file -> hasNextCoordinate(file, offset))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 File 좌표입니다."));
    }

    private boolean hasNextCoordinate(File file, int offset) {
        return file.coordinate == this.coordinate + offset;
    }
}
