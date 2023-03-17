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

    private static final int UP = 1;
    private static final int NO_DIRECTION = 0;
    private static final int DOWN = -1;

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

    public int getDirectionTo(Rank targetRank) {
        if (targetRank.value - this.value == 0) {
            return NO_DIRECTION;
        }
        if (targetRank.value - this.value > 0) {
            return UP;
        }
        return DOWN;
    }

    public int getValueDiff(Rank targetRank) {
        return Math.abs(targetRank.value - this.value);
    }

    public int getValue() {
        return value;
    }
}
