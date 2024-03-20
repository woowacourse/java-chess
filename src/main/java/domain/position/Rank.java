package domain.position;

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

    private final int number;

    Rank(int number) {
        this.number = number;
    }

    public static Rank fromNumber(int number) {
        return Arrays.stream(values())
                .filter(rank -> rank.number == number)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("rejected value: %d - 존재하지 않은 rank입니다.", number)));
    }

    public Rank next(int diff) {
        return fromNumber(this.number + diff);
    }

    public Rank prev() {
        if (this == Rank.ONE) {
            throw new IllegalArgumentException("범위를 벗어난 rank입니다.");
        }
        return fromNumber(this.number - 1);
    }

    int subtract(Rank rank) {
        return this.number - rank.number;
    }
}
