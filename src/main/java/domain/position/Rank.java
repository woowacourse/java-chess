package domain.position;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
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

    public List<Rank> between(Rank target) {
        List<Rank> ranks = IntStream.range(Math.min(this.number, target.number), Math.max(this.number, target.number))
                .skip(1)
                .mapToObj(Rank::fromNumber)
                .collect(Collectors.toList());
        if (this.isUpperThan(target)) {
            Collections.reverse(ranks);
        }
        return ranks;
    }

    boolean isUpperThan(Rank rank) {
        return this.number > rank.number;
    }

    boolean isLowerThan(Rank rank) {
        return this.number < rank.number;
    }

    boolean isSame(Rank rank) {
        return this.number == rank.number;
    }

    int subtract(Rank rank) {
        return this.number - rank.number;
    }

    public int number() {
        return number;
    }
}
