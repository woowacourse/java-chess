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

    private final int value;

    Rank(final int value) {
        this.value = value;
    }

    public static Rank of(final char value) {
        return of(String.valueOf(value));
    }

    public static Rank of(final String value) {
        return of(Integer.parseInt(value));
    }

    public static Rank of(final int value) {
        return Arrays.stream(values())
                .filter(rank -> rank.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "랭크는 %d~%d 사이로 입력해주세요: %d".formatted(RANK_MIN, RANK_MAX, value)));
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

    public boolean canMoveUp() {
        try {
            up();
        } catch (IllegalStateException exception) {
            return false;
        }
        return true;
    }

    public boolean canMoveDown() {
        try {
            down();
        } catch (IllegalStateException exception) {
            return false;
        }
        return true;
    }

    public boolean isRankSeven() {
        return this == SEVEN;
    }

    public boolean isRankTwo() {
        return this == TWO;
    }
}
