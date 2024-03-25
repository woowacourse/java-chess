package chess.domain.chessboard.attribute;

import java.util.Arrays;
import java.util.function.Predicate;

public enum Rank {

    EIGHT, SEVEN, SIX, FIVE, FOUR, THREE, TWO, ONE;

    private static final int RANK_MIN = 1;
    private static final int RANK_MAX = 8;

    public static Rank from(final char value) {
        return from(String.valueOf(value));
    }

    public static Rank from(final String value) {
        return of(value, rank -> value.equals(String.valueOf(RANK_MAX - rank.toRow())));
    }

    public static Rank from(final int row) {
        return of(row, rank -> row == rank.toRow());
    }

    private static <T> Rank of(final T value, final Predicate<Rank> predicate) {
        return Arrays.stream(values())
                .filter(predicate)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "랭크는 %d~%d 사이로 입력해주세요: %s".formatted(RANK_MIN, RANK_MAX, String.valueOf(value))));
    }

    public static boolean isInRange(final int column) {
        return EIGHT.toRow() <= column && column <= ONE.toRow();
    }

    public int toRow() {
        return ordinal();
    }

    @Override
    public String toString() {
        return String.valueOf(RANK_MAX - toRow());
    }
}
