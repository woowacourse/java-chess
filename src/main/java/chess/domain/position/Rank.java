package chess.domain.position;

import java.util.Arrays;

public enum Rank{

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

    public static Rank fromSymbol(final char symbol) {
        return Arrays.stream(values())
                .filter(rank -> rank.index == symbol - '0')
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 유효한 랭크 입력이 아닙니다."));
    }

    public static Rank fromSymbol(final int index) {
        return Arrays.stream(values())
                .filter(rank -> rank.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 유효한 랭크 입력이 아닙니다."));
    }

    public Rank addIndex(final int index) {
        return fromSymbol(this.index + index);
    }

    public int getDistance(final Rank other) {
        return Math.abs(this.index - other.index);
    }

    public boolean isUp(final Rank other) {
        return this.index > other.index;
    }

    public int getIndex() {
        return index;
    }
}
