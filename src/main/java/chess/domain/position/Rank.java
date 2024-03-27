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
    ONE(1),
    ;

    private final int number;

    Rank(int number) {
        this.number = number;
    }

    public static Rank from(int number) {
        return Arrays.stream(values())
                .filter(rank -> rank.number == number)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 행 번호입니다."));
    }

    public int subtract(Rank other) {
        return number - other.number;
    }

    public Rank createRankByDifferenceOf(int rankDifference) {
        return Arrays.stream(values())
                .filter(rank -> rank.number == number + rankDifference)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 행 번호입니다."));
    }
}
