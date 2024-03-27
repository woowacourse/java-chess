package chess.domain.position;

import java.util.HashMap;
import java.util.Map;

public enum Rank {
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1);

    private static final Map<Integer, Rank> CACHE;

    static {
        CACHE = new HashMap<>();
        for (Rank rank : Rank.values()) {
            CACHE.put(rank.rankRow, rank);
        }
    }

    final int rankRow;

    Rank(int rankRow) {
        this.rankRow = rankRow;
    }

    public int calculateDifference(Rank otherRank) {
        return otherRank.rankRow - this.rankRow;
    }

    public Rank move(int moveUnit) {
        int destination = rankRow + moveUnit;
        return CACHE.get(destination);
    }
}
