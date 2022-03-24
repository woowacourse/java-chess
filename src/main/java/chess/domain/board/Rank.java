package chess.domain.board;

import java.util.Arrays;

public enum Rank {
    EIGHT("8"),
    SEVEN("7"),
    SIX("6"),
    FIVE("5"),
    FOUR("4"),
    THREE("3"),
    TWO("2"),
    ONE("1"),
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

    public int calculateDifference(final Rank anotherRank) {
        return Math.abs(this.ordinal() - anotherRank.ordinal());
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
