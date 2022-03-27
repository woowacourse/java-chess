package chess.domain.postion;

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

    private final char name;

    File(char name) {
        this.name = name;
    }

    public static File from(char candidate) {
        return Arrays.stream(File.values())
                .filter(file -> file.getName() == candidate)
                .findAny()
                .orElse(null);
    }

    public char getName() {
        return name;
    }

    public int calculateAbsoluteValue(File file) {
        return Math.abs(name - file.getName());
    }
}
