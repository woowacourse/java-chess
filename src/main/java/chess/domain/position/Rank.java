package chess.domain.position;

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

    public static Rank of(int value) {
        return Arrays.stream(values())
                .filter(rank -> rank.value == value)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당하는 랭크 값이 존재하지 않습니다."));
    }

    public int getValuePoint(Rank targetRank) {
        if (targetRank.value - this.value == 0) {
            return 0;
        }
        if (targetRank.value - this.value > 0) {
            return 1;
        }
        return -1;
    }

    public int getValueDiff(Rank targetRank) {
        return Math.abs(targetRank.value - this.value);
    }

    public int getValue() {
        return value;
    }
}
