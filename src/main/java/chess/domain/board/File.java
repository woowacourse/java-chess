package chess.domain.board;

import java.util.Arrays;

public enum File {

    A('a'),
    B('b'),
    C('c'),
    D('d'),
    E('e'),
    F('f'),
    G('g'),
    H('h'),

    ;

    private static final char MIN_VALUE_RANGE = 'a';
    private static final char MAX_VALUE_RANGE = 'h';

    private final int value;


    File(int value) {
        this.value = value;
    }

    public static File from(int value) {
        return Arrays.stream(values())
                .filter(file -> file.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효한 범위가 아닙니다."));
    }

    File move(int weight) {
        return Arrays.stream(File.values())
                .filter(it -> it.value == value + weight)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효한 범위를 초과 했습니다."));
    }

    boolean canMove(int weight) {
        return value + weight >= MIN_VALUE_RANGE && value + weight <= MAX_VALUE_RANGE;
    }

    int compare(File other) {
        return Integer.compare(value, other.value);
    }

}
