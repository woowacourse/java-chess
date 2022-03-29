package chess.domain.postion;

import java.util.Arrays;

public enum File {
    NOTHING('0'),
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
                .orElse(NOTHING);
    }

    public int calculateAbsoluteValue(File file) {
        return Math.abs(name - file.getName());
    }

    public boolean isNothing() {
        return this.equals(NOTHING);
    }

    public char getName() {
        return name;
    }
}
