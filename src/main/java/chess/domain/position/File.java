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
