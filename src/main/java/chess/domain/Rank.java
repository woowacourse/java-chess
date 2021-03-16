package chess.domain;

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

    private final int rank;

    Rank(int rank) {
        this.rank = rank;
    }

    public static Rank of(int rank) {
        return Arrays.stream(values())
            .filter(value -> rank == value.rank)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 범위를 벗어났습니다."));
    }

    public int getRank() {
        return rank;
    }
}
