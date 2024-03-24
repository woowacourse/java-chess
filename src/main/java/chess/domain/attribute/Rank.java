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
    ONE(1);

    private static final int RANK_MAX = 8;
    private static final int RANK_MIN = 1;

    private final int row;

    Rank(final int row) {
        this.row = row;
    }

    public static Rank of(final char row) {
        return of(String.valueOf(row));
    }

    public static Rank of(final String row) {
        return of(Integer.parseInt(row));
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

    public Rank up() {
        int upOrdinal = this.ordinal() - 1;
        Rank[] files = Rank.values();
        try {
            return files[upOrdinal];
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new IllegalStateException("해당 칸의 위칸이 존재하지 않습니다.");
        }
    }

    public Rank down() {
        int downOrdinal = this.ordinal() + 1;
        Rank[] files = Rank.values();
        try {
            return files[downOrdinal];
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new IllegalStateException("해당 칸의 아래칸이 존재하지 않습니다.");
        }
    }
}
