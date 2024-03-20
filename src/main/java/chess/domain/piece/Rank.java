package chess.domain.piece;

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

    private final int number;

    Rank(int number) {
        this.number = number;
    }

    public static Rank from(final int input) {
        return Arrays.stream(values())
                .filter(rank -> rank.number == input)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 유효한 랭크 입력이 아닙니다."));
    }

    public int minus(final Rank other) {
        return this.number - other.number;
    }

    public int getDistance(final Rank other) {
        return Math.abs(this.number - other.number);
    }

    public int getNumber() {
        return number;
    }
}
