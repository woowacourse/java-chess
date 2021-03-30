package chess.domain.board;

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

    @Override
    public String toString() {
        return String.valueOf(rank);
    }
}
