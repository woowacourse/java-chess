package chess.domain;

import java.util.Arrays;

public enum Rank {
    ONE(1, "1"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8");

    private final int sequence;
    private final String name;

    Rank(int sequence, String name) {
        this.sequence = sequence;
        this.name = name;
    }

    public static Rank findRank(String name) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 랭크를 찾을 수 없습니다."));
    }

    public static boolean isInChessBoardRange(int rankValue) {
        return Arrays.stream(Rank.values())
                .map(Rank::getSequence)
                .anyMatch(rank -> rank == rankValue);
    }

    public String getName() {
        return name;
    }

    public int getSequence() {
        return sequence;
    }
}
