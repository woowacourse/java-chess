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

    static final String RANK_NOT_FOUND_MESSAGE = "일치하는 Rank를 찾을 수 없습니다.";

    private final int value;

    Rank(final int value) {
        this.value = value;
    }

    static Rank from(int value) {
        return Arrays.stream(values())
                .filter(it -> it.value == value)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(RANK_NOT_FOUND_MESSAGE));
    }

    public int value() {
        return value;
    }

    public int calculateRankGap(final Rank subtrahend) {
        return value - subtrahend.value;
    }
}
