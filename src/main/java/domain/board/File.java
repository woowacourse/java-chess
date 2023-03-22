package domain.board;

import java.util.Arrays;

public enum File {
    A('a'),
    B('b'),
    C('c'),
    D('d'),
    E('e'),
    F('f'),
    G('g'),
    H('h');

    private final char value;

    File(char value) {
        this.value = value;
    }

    public static File find(int fileCoordinate) {
        return Arrays.stream(File.values())
            .filter(file -> file.ordinal() == fileCoordinate)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 좌표입니다."));
    }

    public static File find(char value) {
        return Arrays.stream(File.values())
            .filter(file -> file.value == value)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 좌표입니다."));
    }
}
