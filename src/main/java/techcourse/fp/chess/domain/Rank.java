package techcourse.fp.chess.domain;

import java.util.Arrays;

public enum Rank {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private final int order;

    Rank(final int order) {
        this.order = order;
    }

    public static Rank of(final int order) {
        return Arrays.stream(values())
                .filter(rank -> rank.order == order)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Rank의 범위를 초과하였습니다."));
    }

    public int getOrder() {
        return order;
    }
}
