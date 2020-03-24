package chess;

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

    private int rank;

    Rank(int rank) {
        this.rank = rank;
    }

    public static Rank of(String rank) {
        return Arrays.stream(values())
                .filter(pv -> pv.rank == Integer.parseInt(rank))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 y 좌표값을 입력하였습니다."));
    }
}
