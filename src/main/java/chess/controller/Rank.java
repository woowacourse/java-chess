package chess.controller;

import java.util.Arrays;

public enum Rank {
    ONE("1", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8);

    private final String rawRank;
    private final int value;

    Rank(String rawRank, int value) {
        this.rawRank = rawRank;
        this.value = value;
    }

    public static int findRank(String input) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.rawRank.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 행입니다."))
                .value;
    }
}
