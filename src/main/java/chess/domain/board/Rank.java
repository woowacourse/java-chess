package chess.domain.board;

import java.util.Arrays;

public enum Rank {

    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),

    ;

    private static final char MIN_VALUE_RANGE = 1;
    private static final char MAX_VALUE_RANGE = 8;

    private final int value;


    Rank(int value) {
        this.value = value;
    }

    public static Rank from(int value) {
        return Arrays.stream(values())
                .filter(rank -> rank.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효한 범위가 아닙니다."));
    }

    public Rank move(int weight) {
        return Arrays.stream(Rank.values())
                .filter(it -> it.value == value + weight)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효한 범위를 초과 했습니다."));
    }

    boolean canMove(int weight) {
        return value + weight >= MIN_VALUE_RANGE && value + weight <= MAX_VALUE_RANGE;
    }

    public int compare(Rank other) {
        return Integer.compare(value, other.value);
    }

}
