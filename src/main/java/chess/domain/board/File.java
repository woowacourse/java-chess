package chess.domain.board;

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

    private static final int NEAR_INDEX = 1;
    private final int value;

    File(final int value) {
        this.value = value;
    }

    public static File from(final int value) {
        return Arrays.stream(File.values())
                .filter(file -> file.value == value)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 위치입니다"));
    }

    public File plus() {
        int filePlused = this.value + NEAR_INDEX;
        return Arrays.stream(File.values())
                .filter(file -> file.value == filePlused)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 위치입니다"));
    }

    public File minus() {
        int fileMinused = this.value - NEAR_INDEX;
        return Arrays.stream(File.values())
                .filter(file -> file.value == fileMinused)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 위치입니다"));
    }

    public boolean isOver(final File file) {
        return value > file.value;
    }

    public int sub(final File file) {
        return this.value - file.value;
    }

    public int getValue() {
        return value;
    }
}
