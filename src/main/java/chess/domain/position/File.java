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

    private final int value;

    File(final int value) {
        this.value = value;
    }

    public static File from(final String value) {
        return File.from(Integer.parseInt(value));
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

    public String getValue() {
        return Integer.toString(value);
    }
}
