package domain.position;

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

    private final int index;

    Rank(final int index) {
        this.index = index;
    }

    public static Rank of(final int index) {
        return Arrays.stream(values())
            .filter(value -> value.index == index)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 올바르지 않은 Rank입니다.(1 ~ 8)"));
    }

    public static Rank of(final String index) {
        return Rank.of(Integer.parseInt(index));
    }

    public static boolean isRankRange(final int index) {
        return ONE.index <= index && index <= EIGHT.index;
    }

    public int getIndex() {
        return index;
    }
}
