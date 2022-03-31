package chess.domain.position;

import java.util.stream.Stream;

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

    public static File from(char value) {
        return Stream.of(File.values())
                .filter(file -> file.getValue() == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 file 범위 입니다."));
    }

    public int calculateFileInAbsolute(File otherFile) {
        return Math.abs(value - otherFile.getValue());
    }

    public char getValue() {
        return value;
    }
}
