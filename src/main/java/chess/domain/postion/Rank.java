package chess.domain.postion;

import java.util.Arrays;

public enum Rank {
    NOTHING(0),
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

    public static Rank from(int candidate) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.getNumber() == candidate)
                .findAny()
                .orElse(NOTHING);
    }

    public int calculateDifference(final Rank rank) {
        return number - rank.getNumber();
    }

    public int calculateAbsoluteValue(Rank rank) {
        return Math.abs(number - rank.getNumber());
    }

    public boolean isNothing() {
        return this.equals(NOTHING);
    }

    public int getNumber() {
        return number;
    }
}
