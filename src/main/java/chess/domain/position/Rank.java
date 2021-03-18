package chess.domain.position;

import java.util.Arrays;

public enum Rank {
    EMPTY(0, ""),
    ONE(1, "1"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8");

    private final int value;
    private final String rank;

    Rank(final int value, final String rank) {
        this.value = value;
        this.rank = rank;
    }

    public static Rank findByValue(final int target) {
        return Arrays.stream(values())
                .filter(value -> value.value == target)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("없는 랭크임! 입력 값: %d", target)));
    }

    public static Rank findByRank(final String rank) {
        return Arrays.stream(values())
                .filter(value -> value.rank.equals(rank))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("없는 랭크임! 입력 값: %s", rank)));
    }

    public String getRank() {
        return rank;
    }

    public int getValue() {
        return value;
    }
}
