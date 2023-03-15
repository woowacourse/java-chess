package chess.board;

import java.util.Arrays;

public enum Rank {
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1),
    ;

    private final int index;

    Rank(final int index) {
        this.index = index;
    }

    public static Rank of(final int index) {
        return Arrays.stream(values())
                .filter(rank -> rank.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Rank의 index는 1~8이여야합니다."));
    }

    public int getIndex() {
        return index;
    }
}
