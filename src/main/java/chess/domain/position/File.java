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
    private final char file;

    File(char file) {
        this.file = file;
    }

    public char getFile() {
        return file;
    }

    public static boolean isFile(char candidate) {
        return Arrays.stream(File.values())
                .filter(file -> file.getFile() == candidate)
                .findAny()
                .isPresent();
    }

    public int calculateAbsoluteValue(File other) {
        return Math.abs(file - other.getFile());
    }
}
