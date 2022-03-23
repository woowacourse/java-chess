package chess;

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

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public static Rank of(String input) {
        return Arrays.stream(values())
                .filter(rank -> rank.value == Integer.parseInt(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재 하지 않는 랭크입니다."));
    }
}
