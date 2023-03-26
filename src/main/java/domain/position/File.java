package domain.position;

import java.util.Arrays;

public enum File {
    A('A'),
    B('B'),
    C('C'),
    D('D'),
    E('E'),
    F('F'),
    G('G'),
    H('H'),
    NOTHING(' ');

    private final char value;

    File(final char value) {
        this.value = value;
    }

    public File move(final int distance) {
        final int resultFile = value + distance;

        return Arrays.stream(File.values())
                .filter(file -> file.value == resultFile)
                .findAny()
                .orElse(NOTHING);
    }

    public int getDifference(final File other) {
        return other.value - this.value;
    }
}
