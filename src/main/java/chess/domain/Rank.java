package chess.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Rank {
    ONE("1", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8);

    private final String rankName;
    private final int rankOrder;

    Rank(String rankName, int rankOrder) {
        this.rankName = rankName;
        this.rankOrder = rankOrder;
    }

    public static Rank from(String rankName) {
        return Arrays.stream(Rank.values())
                .filter(it -> it.rankName.equals(rankName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 rank 입니다"));
    }

    public Rank from(int difference) {
        return Arrays.stream(Rank.values())
                .filter(it -> it.rankOrder == rankOrder + difference)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 rank 입니다"));
    }

    public int getDifference(Rank other) {
        return other.rankOrder - rankOrder;
    }

    private boolean isBetween(Rank current, Rank other) {
        if (rankOrder < current.rankOrder && current.rankOrder < other.rankOrder) {
            return true;
        }
        return rankOrder > current.rankOrder && current.rankOrder > other.rankOrder;
    }

    public List<Rank> createPath(Rank other) {
        int rankDifference = getDifference(other);
        if (rankDifference == 0) {
            return new ArrayList<>();
        }
        int unit = rankDifference / Math.abs(rankDifference);
        List<Rank> result = new ArrayList<>();
        int current = unit;
        while (current != rankDifference) {
            Rank rank = from(current);
            result.add(rank);
            current += unit;
        }
        return result;
    }
}
