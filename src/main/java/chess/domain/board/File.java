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

    private final int value;

    File(final int value) {
        this.value = value;
    }

    public File plus() {
        int filePlused = this.value + 1;
        return Arrays.stream(File.values())
                .filter(file -> file.value == filePlused)
                .findAny()
                .orElseThrow(IllegalStateException::new);
    }

    public File minus() {
        int fileMinused = this.value - 1;
        return Arrays.stream(File.values())
                .filter(file -> file.value == fileMinused)
                .findAny()
                .orElseThrow(IllegalStateException::new);
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
