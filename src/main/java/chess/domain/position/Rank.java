package chess.domain.position;

import java.util.Arrays;

public enum Rank {
    ONE('1', 1),
    TWO('2', 2),
    THREE('3', 3),
    FOUR('4', 4),
    FIVE('5', 5),
    SIX('6', 6),
    SEVEN('7', 7),
    EIGHT('8', 8);

    private static final String BUG_MESSAGE_BOUND = "[BUG] 체스판 범위를 벗어났습니다.";
    private final char value;
    private final int index;

    Rank(char value, int index) {
        this.value = value;
        this.index = index;
    }

    static Rank find(char value) {
        return Arrays.stream(values())
                .filter(rank -> rank.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(BUG_MESSAGE_BOUND));
    }

    int getGap(Rank target) {
        return target.index - this.index;
    }

    Rank add(int dRank) {
        return Arrays.stream(values())
                .filter(rank -> rank.index == this.index + dRank)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(BUG_MESSAGE_BOUND));
    }
}
