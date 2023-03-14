package chess.domain;

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

    File(final char value) {
        this.value = value;
    }

    public static File from(final char value) {
        return Arrays.stream(File.values())
                .filter(file -> file.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 File입니다."));
    }
}
