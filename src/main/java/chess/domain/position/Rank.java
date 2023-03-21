package chess.domain.position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Rank {
    ONE("1", 0),
    TWO("2", 1),
    THREE("3", 2),
    FOUR("4", 3),
    FIVE("5", 4),
    SIX("6", 5),
    SEVEN("7", 6),
    EIGHT("8", 7);

    private static final Map<Integer, Rank> RANK_BY_INDEX = new HashMap<>();
    private static final Map<String, Rank> RANK_BY_CODE = new HashMap<>();

    static {
        for (Rank rank : values()) {
            RANK_BY_INDEX.put(rank.index, rank);
            RANK_BY_CODE.put(rank.code, rank);
        }
    }

    private final String code;
    private final int index;

    Rank(final String code, final int index) {
        this.code = code;
        this.index = index;
    }

    public static Rank findByCode(final String code) {
        return RANK_BY_CODE.get(code);
    }

    public int calculateDistance(final Rank otherRank) {
        return Math.abs(this.index - otherRank.index);
    }

    public List<Rank> getRanksTo(final Rank otherRank) {
        final List<Rank> ascendingRanks = generateAscendingRanks(otherRank);
        if (this.index < otherRank.index) {
            return ascendingRanks;
        }
        return reverse(ascendingRanks);
    }

    private static List<Rank> reverse(final List<Rank> ranks) {
        Collections.reverse(ranks);
        return ranks;
    }

    private List<Rank> generateAscendingRanks(final Rank otherRank) {
        final int maxIndex = Math.max(this.index, otherRank.index);
        final int minIndex = Math.min(this.index, otherRank.index);

        final List<Rank> passingRanks = new ArrayList<>();
        for (int index = minIndex + 1; index < maxIndex; index++) {
            passingRanks.add(findByIndex(index));
        }
        return passingRanks;
    }

    private Rank findByIndex(final int index) {
        return RANK_BY_INDEX.get(index);
    }

    public boolean isUpperThan(final Rank otherRank) {
        return this.index > otherRank.index;
    }

    public boolean isLowerThan(final Rank otherRank) {
        return this.index < otherRank.index;
    }

    public String getCode() {
        return code;
    }
}
