package domain.game;

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

    private final int order;

    Rank(int order) {
        this.order = order;
    }

    public int calculateIncrement(Rank targetRank) {
        return targetRank.order - this.order;
    }

    public Rank getNext() {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.order == this.order + 1)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("서버 내부 에러 - 다음 Rank는 존재하지 않습니다"));
    }

    public Rank getPrevious() {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.order == this.order - 1)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("서버 내부 에러 - 이전 Rank는 존재하지 않습니다"));
    }
}
