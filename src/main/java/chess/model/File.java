package chess.model;

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

    private final int row;

    File(int row) {
        this.row = row;
    }

    public static File of(int row) {
        return Arrays.stream(values())
                .filter(file -> file.row == row)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 위치입니다 : " + row));
    }

    public File add(int row) {
        return File.of(this.row + row);
    }

    public int calculateGap(File file) {
        return file.row - this.row;
    }

    public String getName() {
        return this.name().toLowerCase();
    }
}
