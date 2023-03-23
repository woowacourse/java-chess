package chess.model.position;

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

    public static File findFile(final String value) {
        return Arrays.stream(values())
                .filter(file -> file.toString().equalsIgnoreCase(value)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 열입니다."));
    }

    public static File findFile(final int value) {
        return Arrays.stream(values())
                .filter(file -> file.value == value).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 열입니다."));
    }

    public int differ(final File other) {
        return this.value - other.value;
    }

    public File next(final int direction) {
        int value = this.value + direction;
        return findFile(value);
    }

    public int value() {
        return value;
    }
}
