package chess.domain.position;

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

    Rank(final int rank) {
        this.rank = rank;
    }

    public static Rank findByRank(final int rank) {
        return Arrays.stream(values())
                .filter(value -> value.rank == rank)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("없는 랭크임! 입력 값: %d", rank)));
    }
}
