package chess.domain.position;

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

    public static File from(final int value) {
        return Arrays.stream(File.values())
                .filter(file -> file.value == value)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 위치입니다"));
    }

    public File moveTo(final int distance) {
        return File.from(this.value - distance);
    }

    public int sub(final File file) {
        return this.value - file.value;
    }
}
