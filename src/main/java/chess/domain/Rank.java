package chess.domain;

import java.util.Arrays;

public enum Rank {
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIZ("6"),
    SEVEN("7"),
    EIGHT("8");

    private final String rankName;

    Rank(String rankName) {
        this.rankName = rankName;
    }

    public static Rank from(String rankName) {
        return Arrays.stream(Rank.values())
                .filter(it -> it.rankName.equals(rankName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 rank 입니다"));
    }
}
