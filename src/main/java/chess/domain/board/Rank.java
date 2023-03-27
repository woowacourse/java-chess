package chess.domain.board;

import java.util.Arrays;

public enum Rank {
    ONE(1),
    TWO(2),
    TREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private final int rank;

    Rank(int rank) {
        this.rank = rank;
    }

    public static Rank of(int inputRank) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.rank == inputRank)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("범위를 벗어난 값입니다."));
    }

    public int getRank() {
        return rank;
    }
}
