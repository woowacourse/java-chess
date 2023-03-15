package chess.domain;

import java.util.Arrays;

public enum Rank {
    ONE("1", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIZ("6", 6),
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

    public int getDifference(Rank other) {
        return other.rankOrder - rankOrder;
    }
}
