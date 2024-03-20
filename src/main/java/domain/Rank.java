package domain;

import java.util.Arrays;

public enum Rank {
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1);

    private final int index;

    Rank(final int index) {
        this.index = index;
    }

    public static Rank from(final String input) {
        return Arrays.stream(values())
                .filter(rank -> rank.index == Integer.parseInt(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("랭크가 없습니다."));
    }

    public int subtrack(final Rank other) {
        return this.index - other.index;
    }

    public Rank move(final int i) {
        return Arrays.stream(values())
                .filter(rank -> rank.index == this.index + i)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 랭크입니다."));
    }

    public int getIndex() {
        return index;
    }
}
