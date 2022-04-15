package chess.model.square;

import java.util.Arrays;

public enum Rank {

    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    ;

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public static Rank findRank(int rawRank) {
        return Arrays.stream(values())
                .filter(rank -> rank.value == rawRank)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Rank 입니다: " + rawRank));
    }

    public boolean availableLocation(int distance) {
        return Arrays.stream(Rank.values())
                .anyMatch(rank -> rank.value == (this.value + distance));
    }

    public Rank nextTo(Integer value) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.value == this.value + value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Rank 입니다: " + value));
    }

    public int value() {
        return value;
    }
}
