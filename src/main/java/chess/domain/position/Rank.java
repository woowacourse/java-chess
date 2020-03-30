package chess.domain.position;

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

    private final int symbol;

    Rank(int symbol) {
        this.symbol = symbol;
    }

    public static Rank of(String rank) {
        return of(Integer.parseInt(rank));
    }

    public static Rank of(int rank) {
        return Arrays.stream(values())
                .filter(f -> f.symbol == rank)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 y 좌표값을 입력하였습니다."));
    }

    public int getSymbol() {
        return this.symbol;
    }

    public int calculateGap(Rank rank) {
        return this.symbol - rank.symbol;
    }
}
