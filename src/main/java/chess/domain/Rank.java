package chess.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum Rank {
    ONE("1", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8);
    private static final int SKIP_FIRST = 1;

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

    public static Rank from(int rankOrder) {
        return Arrays.stream(Rank.values())
                .filter(it -> it.rankOrder == rankOrder)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 rank 입니다"));
    }

    public int getDifference(Rank other) {
        return other.rankOrder - rankOrder;
    }

    public List<Rank> createPath(Rank other) {
        List<Rank> ranks = IntStream.range(Math.min(rankOrder, other.rankOrder), Math.max(rankOrder, other.rankOrder))
                .skip(SKIP_FIRST)
                .mapToObj(Rank::from)
                .collect(Collectors.toList());
        if (rankOrder > other.rankOrder) {
            Collections.reverse(ranks);
        }
        return ranks;
    }
}
