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

    private final int index;

    Rank(int index) {
        this.index = index;
    }

    public static Rank from(final int input) {
        return Arrays.stream(values())
                .filter(rank -> rank.index == input)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 유효한 랭크 입력이 아닙니다."));
    }

    public int minus(final Rank other) {
        return this.index - other.index;
    }

    public int getDistance(final Rank other) {
        return Math.abs(this.index - other.index);
    }

    public int getIndex() {
        return index;
    }
}
