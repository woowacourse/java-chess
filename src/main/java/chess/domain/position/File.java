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
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 File 값 입니다."));
    }

    public int calculateDistance(final File file) {
        return value - file.value;
    }

    public File move(final int distance) {
        try {
            return of((char) (value + (char) distance));
        } catch (IllegalArgumentException exception) {
            return null;
        }
    }
}
