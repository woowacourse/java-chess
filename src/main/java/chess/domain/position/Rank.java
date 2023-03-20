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

    private final int value;

    Rank(final int value) {
        this.value = value;
    }

    public static Rank of(final int value) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 Rank가 존재하지 않습니다."));
    }

    public static Rank of(final char value) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.value == (value - '0'))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 Rank가 존재하지 않습니다."));
    }

    public int value() {
        return value;
    }
}
