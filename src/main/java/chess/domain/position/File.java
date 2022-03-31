package chess.domain.position;

import java.util.Arrays;

public enum File {

    A(0),
    B(1),
    C(2),
    D(3),
    E(4),
    F(5),
    G(6),
    H(7);

    public static final char FIRST_FILE_VALUE = 'a';
    private final int value;

    File(final int value) {
        this.value = value;
    }

    public static File from(final char value) {
        return from(value - FIRST_FILE_VALUE);
    }

    public static File from(final int value) {
        return Arrays.stream(File.values())
                .filter(file -> file.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 File 값 입니다."));
    }

    public int calculateDistance(final File file) {
        return value - file.value;
    }

    public File move(final int distance) {
        return from(value + distance);
    }
}
