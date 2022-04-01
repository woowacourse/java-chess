package chess.domain.position;

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

    private static final String CAN_NOT_FIND_RANK = "[ERROR] 일치하는 Rank 값을 찾을 수 없습니다.";

    private final int value;

    Rank(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Rank findRank(final int value) {
        return Arrays.stream(Rank.values())
            .filter(rank -> rank.value == value)
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(CAN_NOT_FIND_RANK));
    }
}
