package chess.board;

import java.util.Arrays;

public enum Rank {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private final int value;

    Rank(final int value) {
        this.value = value;
    }

    public static Rank findByValue(int value) {
        return Arrays.stream(values())
                .filter(aRank -> aRank.value == value)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public int subtract(Rank rank) {
        return value - rank.value;
    }
}
