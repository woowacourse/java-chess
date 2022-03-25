package chess.domain.board;

import java.util.Arrays;

public enum Rank {
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    ;

    private final String value;

    Rank(final String value) {
        this.value = value;
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
        final int difference = this.ordinal() - anotherRank.ordinal();
        if (absoluteFlag) {
            return Math.abs(difference);
        }
        return difference;
    }

    public Rank next(final Rank targetRank) {
        if (this.ordinal() < targetRank.ordinal()) {
            return values()[this.ordinal() + 1];
        }
        if (this.ordinal() > targetRank.ordinal()) {
            return values()[this.ordinal() - 1];
        }
        return this;
    }
}
