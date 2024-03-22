package domain.position;

import java.util.Arrays;

public enum Rank {
    ONE(7),
    TWO(6),
    THREE(5),
    FOUR(4),
    FIVE(3),
    SIX(2),
    SEVEN(1),
    EIGHT(0);

    private final int index;

    Rank(final int index) {
        this.index = index;
    }

    public static Rank of(final int index) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.getIndex() == index)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 인덱스입니다."));
    }

    public int getIndex() {
        return index;
    }
}
