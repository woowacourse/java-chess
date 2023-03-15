package chess.domain.board;

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

    private final int index;

    Rank(final int index) {
        this.index = index;
    }

    public static Rank from(final int index) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 랭크 인덱스입니다."));
    }

    public int calculateDistance(final Rank rank) {
        return this.index - rank.index;
    }

    public Rank next() {
        return Rank.from(index + 1);
    }

    public Rank prev() {
        return Rank.from(index - 1);
    }
}
