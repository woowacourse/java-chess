package chess.model.position;

import java.util.Arrays;

public enum Rank {

    FIRST(1),
    SECOND(2),
    THIRD(3),
    FOURTH(4),
    FIFTH(5),
    SIXTH(6),
    SEVENTH(7),
    EIGHTH(8);

    private final int value;

    Rank(final int value) {
        this.value = value;
    }

    public static Rank findRank(final int value) {
        return Arrays.stream(values())
                .filter(rank -> rank.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 행입니다."));
    }

    public Rank next(final int rank) {
        final int nextValue = this.value + rank;
        return findRank(nextValue);
    }

    public int differ(final Rank other) {
        return this.value - other.value;
    }

    public int value() {
        return value;
    }
}
