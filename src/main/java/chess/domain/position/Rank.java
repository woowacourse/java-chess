package chess.domain.position;

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

    public int getValue() {
        return value;
    }

    public static Rank findRank(int value) {
        return Arrays.stream(Rank.values())
            .filter(rank -> rank.value == value)
            .findAny()
            .orElseThrow();
    }
}
