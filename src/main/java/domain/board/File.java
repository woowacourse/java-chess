package domain.board;

import java.util.Arrays;

public enum File {
    A('a', 0),
    B('b', 1),
    C('c', 2),
    D('d', 3),
    E('e', 4),
    F('f', 5),
    G('g', 6),
    H('h', 7);

    private final char value;

    private final int index;

    File(char value, int index) {
        this.value = value;
        this.index = index;
    }

    public static File find(int fileCoordinate) {
        return Arrays.stream(File.values())
            .filter(file -> file.index == fileCoordinate)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 좌표입니다."));
    }

    public static File find(char value) {
        return Arrays.stream(File.values())
            .filter(file -> file.value == value)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 좌표입니다."));
    }

    public int getIndex() {
        return index;
    }
}
