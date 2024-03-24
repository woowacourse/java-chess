package chess.domain.square;

import java.util.Arrays;

public enum Rank {
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    ;

    public static final String INVALID_RANK = "유효하지 않는 랭크입니다.";

    public static Rank from(final String rank) {
        return Arrays.stream(Rank.values())
                .filter(value -> (value.ordinal() + 1) == Integer.parseInt(rank))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_RANK));
    }

    public Rank from(final int rankMoveStep) {
        return indexOf(this.ordinal() + rankMoveStep);
    }

    private Rank indexOf(final int index) {
        return Arrays.stream(values())
                .filter(rank -> rank.ordinal() == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_RANK));
    }
}
