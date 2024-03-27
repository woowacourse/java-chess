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

    public static Rank fromNumber(final int number) {
        return Arrays.stream(values())
                .filter(rank -> rank.number == number)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 rank입니다."));
    }

    public List<Rank> between(final Rank target) {
        final List<Rank> ranks = IntStream.range(Math.min(this.number, target.number), Math.max(this.number, target.number))
                .skip(1)
                .mapToObj(Rank::fromNumber)
                .collect(Collectors.toList());
        if (this.isUpperThan(target)) {
            Collections.reverse(ranks);
        }
        return ranks;
    }

    boolean isUpperThan(final Rank rank) {
        return this.number > rank.number;
    }

    boolean isLowerThan(final Rank rank) {
        return this.number < rank.number;
    }

    boolean isSame(final Rank rank) {
        return this.number == rank.number;
    }

    int subtract(final Rank rank) {
        return this.number - rank.number;
    }

    public String number() {
        return String.valueOf(number);
    }
}
