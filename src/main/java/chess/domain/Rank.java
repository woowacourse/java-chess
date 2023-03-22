package chess.domain;

import java.util.*;

public enum Rank {
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8");

    private static final Map<String, Rank> RANK_BY_NAME = new HashMap<>();

    static {
        for (Rank rank : values()) {
            RANK_BY_NAME.put(rank.name, rank);
        }
    }

    private final String name;

    Rank(final String name) {
        this.name = name;
    }

    public static Rank valueOfName(final String name) {
        validateRankName(name);
        return RANK_BY_NAME.get(name);
    }

    private static void validateRankName(final String name) {
        if (!RANK_BY_NAME.containsKey(name)) {
            throw new IllegalArgumentException("해당 위치는 존재하지 않습니다. 입력 값 :" + name);
        }
    }

    public int calculateDistance(final Rank otherRank) {
        return Math.abs(this.ordinal() - otherRank.ordinal());
    }

    public List<Rank> getRanksTo(final Rank otherRank) {
        final List<Rank> ascendingRanks = generateAscendingRanks(otherRank);
        if (this.ordinal() < otherRank.ordinal()) {
            return ascendingRanks;
        }
        return reverse(ascendingRanks);
    }

    private static List<Rank> reverse(final List<Rank> ranks) {
        Collections.reverse(ranks);
        return ranks;
    }

    private List<Rank> generateAscendingRanks(final Rank otherRank) {
        final int maxOrder = Math.max(this.ordinal(), otherRank.ordinal());
        final int minOrder = Math.min(this.ordinal(), otherRank.ordinal());

        final List<Rank> passingRanks = new ArrayList<>();
        for (int order = minOrder + 1; order < maxOrder; order++) {
            passingRanks.add(findByOrdinal(order));
        }
        return passingRanks;
    }

    private Rank findByOrdinal(final int order) {
        return values()[order];
    }

    public boolean isUpperThan(final Rank otherRank) {
        return this.ordinal() > otherRank.ordinal();
    }

    public boolean isLowerThan(final Rank otherRank) {
        return this.ordinal() < otherRank.ordinal();
    }
}
