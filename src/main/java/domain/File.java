package domain;

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

    private final int index;

    File(final int index) {
        this.index = index;
    }

    public static File from(final String input) {
        return Arrays.stream(values())
                .filter(file -> file.name().equals(input.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 파일입니다."));
    }

    public static File from(final int index) {
        return Arrays.stream(values())
                .filter(file -> file.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(index + "번 파일은 존재하지 않습니다."));
    }


    public File move(final int vector) {
        return Arrays.stream(values())
                .filter(file -> file.index == this.index + vector)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 파일입니다."));
    }

    public boolean canMove(final int row) {
        for (final File value : values()) {
            if (value.index == this.index + row) {
                return true;
            }
        }
        return false;
    }
}
