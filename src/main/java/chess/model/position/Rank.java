package chess.model.position;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum Rank {

    FIRST(1),
    SECOND(2),
    THIRD(3),
    FOURTH(4),
    FIFTH(5),
    SIXTH(6),
    SEVENTH(7),
    EIGHTH(8);

    private static final Map<Integer, Rank> CACHE = Arrays.stream(values())
            .collect(Collectors.toMap(rank -> rank.value, rank -> rank));

    private final int value;

    Rank(final int value) {
        this.value = value;
    }

    public static Rank findRank(final int value) {
        validateRank(value);

        return CACHE.get(value);
    }

    private static void validateRank(final int value) {
        if (!CACHE.containsKey(value)) {
            throw new IllegalArgumentException("존재하지 않는 행입니다.");
        }
    }

    public int differ(final Rank other) {
        return this.value - other.value;
    }

    public Rank findNextRank(final int offer) {
        final int nextRankValue = this.value + offer;

        return findRank(nextRankValue);
    }

    public int value() {
        return value;
    }
}
