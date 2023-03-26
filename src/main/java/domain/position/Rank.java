package domain.position;

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
    NOTHING(0);

    private final int value;

    Rank(final int value) {
        this.value = value;
    }

    public Rank move(final int distance) {
        final int resultRank = value + distance;

        return Arrays.stream(Rank.values())
                .filter(rank -> rank.value == resultRank)
                .findAny()
                .orElse(NOTHING);
    }

    public int getDifference(final Rank other) {
        return other.value - this.value;
    }

    public int getValue() {
        return value;
    }
}
