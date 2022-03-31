package chess.domain.board;

import java.util.Arrays;

public enum Rank {

    ONE("1", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    ;

    private final String value;
    private final int order;

    Rank(final String value, final int order) {
        this.value = value;
        this.order = order;
    }

    public static Rank from(final String value) {
        return Arrays.stream(values())
                .filter(rank -> rank.value.equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 rank 값입니다."));
    }

    private static Rank from(final int order) {
        return Arrays.stream(values())
                .filter(file -> file.order == order)
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
        if (this.order < targetRank.order) {
            return Rank.from(this.order + 1);
        }
        if (this.ordinal() > targetRank.ordinal()) {
            return Rank.from(this.order - 1);
        }
        return this;
    }
}
