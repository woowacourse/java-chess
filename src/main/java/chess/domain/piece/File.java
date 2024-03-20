package chess.domain.piece;

import java.util.Arrays;

public enum File {

    A('a', 1),
    B('b', 2),
    C('c', 3),
    D('d', 4),
    E('e', 5),
    F('f', 6),
    G('g', 7),
    H('h', 8);

    private final char value;
    private final int index;

    File(char value, int index) {
        this.value = value;
        this.index = index;
    }

    public static File from(final char input) {
        return Arrays.stream(values())
                .filter(file -> file.value == input)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 유효한 파일 입력이 아닙니다."));
    }

    public int getDistance(final File other) {
        return Math.abs(this.index - other.index);
    }
}
