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

    public Rank getAddedRank(int valueToAdd) {
        final int addedRankValue = this.value + valueToAdd;
        return Arrays.stream(values())
                .filter(rank -> rank.value == addedRankValue)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 더한 랭크 값이 존재하지 않습니다."));
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
        return targetRank.value - this.value;
    }

    public int getValue() {
        return value;
    }
}
