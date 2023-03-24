package chess.domain.position;

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

    public static File of(final char value) {
        return Arrays.stream(File.values())
                .filter(file -> file.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 File이 존재하지 않습니다."));
    }

    public char value() {
        return value;
    }
}
