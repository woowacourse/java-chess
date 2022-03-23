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
            .filter(file -> file.getValue() == value)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 File 값 입니다."));
    }

    public File plus() {
        return of((char) (value + (char) 1));
    }

    public File minus() {
        return of((char) (value - (char) 1));
    }

    public char getValue() {
        return value;
    }
}
