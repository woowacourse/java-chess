package chess.domain.board;

import java.util.Arrays;

public enum Rank {
    ONE("1", 0),
    TWO("2", 1),
    THREE("3", 2),
    FOUR("4", 3),
    FIVE("5", 4),
    SIX("6", 5),
    SEVEN("7", 6),
    EIGHT("8", 7),
    ;

    private final String value;
    private final int index;

    Rank(final String value, int index) {
        this.value = value;
        this.index = index;
    }

    public static Rank from(final String value) {
        return Arrays.stream(values())
                .filter(rank -> rank.value.equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 rank 값입니다."));
    }

    public static boolean isPawnRank(final Rank rank) {
        return rank == TWO || rank == SEVEN;
    }

    public int calculateDifference(final Rank anotherRank, final boolean absoluteFlag) {
        final int difference = this.index - anotherRank.index;
        if (absoluteFlag) {
            return Math.abs(difference);
        }
        return difference;
    }

    public Rank next(final Rank targetRank) {
        if (this.index < targetRank.index) {
            return values()[this.index + 1];
        }
        if (this.index > targetRank.index) {
            return values()[this.index - 1];
        }
        return this;
    }
}
