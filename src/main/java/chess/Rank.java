package chess;

import java.util.Arrays;

public enum Rank {

    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1),
    ;

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public Rank add(int row) {
        return Rank.of(value + row);
    }

    public boolean canAdd(int row) {
        return value + row >= ONE.value && value + row <= EIGHT.value;
    }

    public static Rank of(int otherValue) {
        return Arrays.stream(values())
                .filter(rank -> rank.value == otherValue)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 잘못된 값이 입력 되었습니다."));
    }
}
