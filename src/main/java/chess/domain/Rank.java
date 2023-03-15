package chess.domain;

import java.util.Arrays;

public enum Rank {
    EIGHT(7),
    SEVEN(6),
    SIX(5),
    FIVE(4),
    FOUR(3),
    THREE(2),
    TWO(1),
    ONE(0);

    private final int y;

    Rank(final int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public Rank findRank(final int targetRank) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.getY() == targetRank)
                .findFirst()
                .orElseThrow();
    }
}
