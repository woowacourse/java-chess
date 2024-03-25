package domain.board;

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

    private final int index;

    File(final int index) {
        this.index = index;
    }

    public static File of(final String value) {
        return Arrays.stream(values())
                .filter(file -> file.name().equals(value.toUpperCase()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 File 값입니다."));
    }

    public static File of(final int index) {
        return Arrays.stream(values())
                .filter(file -> file.getIndex() == index)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 인덱스입니다."));
    }

    public int getIndex() {
        return this.index;
    }
}
