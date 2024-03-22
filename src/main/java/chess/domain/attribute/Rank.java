package chess.domain.attribute;

import java.util.Arrays;
import java.util.function.Predicate;

public enum Rank {

    EIGHT, SEVEN, SIX, FIVE, FOUR, THREE, TWO, ONE;

    private static final int RANK_MAX = 8;
    private static final int RANK_MIN = 1;

    public static Rank of(final int row) {
        return of(row, rank -> row == rank.toRow());
    }

    public static Rank of(final char value) {
        return of(String.valueOf(value));
    }

    public static Rank of(final String value) {
        return of(value, rank -> value.equals(String.valueOf(RANK_MAX - rank.ordinal())));
    }

    private static <T> Rank of(final T value, final Predicate<Rank> predicate) {
        return Arrays.stream(values())
                .filter(predicate)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "랭크는 %d~%d 사이로 입력해주세요: %s".formatted(RANK_MIN, RANK_MAX, String.valueOf(value))));
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

    public static boolean isInRange(final int rank) {
        return RANK_MIN <= rank && rank <= RANK_MAX;
    }

    public int toRow() {
        return ordinal();
    }
}
