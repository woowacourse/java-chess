package chess.domain;

import java.util.Arrays;

public enum Rank {
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1),
    ;

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public static Rank from(int value) {
        return Arrays.stream(values())
                .filter(rank -> rank.value == value)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Rank입니다."));
    }

    public Rank add(int y) {
        int newY = this.value + y;
        return Rank.from(newY);
    }
}
