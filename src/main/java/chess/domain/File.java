package chess.domain;

import java.util.Arrays;

public enum File {
    a(1),
    b(2),
    c(3),
    d(4),
    e(5),
    f(6),
    g(7),
    h(8),
    ;

    private final int value;

    File(int value) {
        this.value = value;
    }

    public static File from(int value) {
        return Arrays.stream(values())
                .filter(file -> file.value == value)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 File입니다."));
    }

    public File add(int x) {
        int newX = this.value + x;
        return File.from(newX);
    }

    public int getValue() {
        return value;
    }
}
