package chess.domain.coordinate;

import java.util.Arrays;

public enum Rank {
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1);

    private final int value;

    Rank(final int value) {
        this.value = value;
    }

    public static Rank findByValue(int value) {
        return Arrays.stream(values())
                .filter(aRank -> aRank.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("%d : 1 ~ 8 을 벗어났습니다.", value)));
    }

    public int subtract(Rank rank) {
        return value - rank.value;
    }

    public Rank sum(int rankValue) {
        return findByValue(this.value + rankValue);
    }

    public int getValue() {
        return value;
    }
}
