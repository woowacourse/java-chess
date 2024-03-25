package domain.position;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public enum Rank {
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1);

    private static final int DISCARDING_COUNT_FROM_FRONT = 1;

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

    public static Rank fromNumber(char number) {
        return fromNumber(Character.getNumericValue(number));
    }

    public List<Rank> findRanksBetween(Rank other) {
        return IntStream.range(Math.min(this.number, other.number), Math.max(this.number, other.number))
                .skip(DISCARDING_COUNT_FROM_FRONT)
                .mapToObj(Rank::fromNumber)
                .sorted(comparator(other))
                .toList();
    }

    private Comparator<Rank> comparator(Rank other) {
        if (this.isUpperThan(other)) {
            return (r1, r2) -> r2.number - r1.number;
        }
        return Comparator.comparingInt(r -> r.number);
    }

    int subtract(Rank rank) {
        return this.number - rank.number;
    }

    boolean isUpperThan(Rank rank) {
        return this.number > rank.number;
    }

    boolean isLowerThan(Rank rank) {
        return this.number < rank.number;
    }

    public int number() {
        return number;
    }
}
