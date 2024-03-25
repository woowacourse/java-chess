package domain;

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

    private final int index;

    Rank(final int index) {
        this.index = index;
    }

    public static Rank from(final int index) {
        return Arrays.stream(values())
                .filter(rank -> rank.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(index + "번 랭크는 존재하지 않습니다."));
    }

    public Rank move(final int i) {
        return Rank.from(this.index + i);
    }

    public boolean canMove(final int column) {
        for (final Rank value : values()) {
            if (value.index == this.index + column) {
                return true;
            }
        }
        return false;
    }
}
