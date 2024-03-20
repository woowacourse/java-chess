package chess.domain.attribute;

import java.util.Arrays;

public enum Rank {

    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1)
    ;

    private static final int RANK_MAX = 8;
    private static final int RANK_MIN = 1;

    private final int row;

    Rank(final int row) {
        this.row = row;
    }

    public static Rank of(final int row) {
        return Arrays.stream(values())
                .filter(rank -> rank.row == row)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "랭크는 %d~%d 사이로 입력해주세요: %d".formatted(RANK_MIN, RANK_MAX, row)));
    }

    public static Rank startRankOf(final Color color) {
        if (color == Color.WHITE) {
            return ONE;
        }
        return EIGHT;
    }

    public static Rank startPawnRankOf(final Color color) {
        if (color == Color.WHITE) {
            return TWO;
        }
        return SEVEN;
    }

    public static boolean isInRange(final int row) {
        return RANK_MIN <= row && row <= RANK_MAX;
    }

    public int getValue() {
        return row;
    }
}
