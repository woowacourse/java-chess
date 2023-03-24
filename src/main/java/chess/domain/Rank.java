package chess.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public enum Rank {

    ONE(1, "1"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8");

    private final int position;
    private final String symbol;

    Rank(final int position, final String symbol) {
        this.position = position;
        this.symbol = symbol;
    }

    public static Rank from(final String symbol) {
        return Arrays.stream(values())
                .filter(rank -> Objects.equals(symbol, rank.symbol))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 랭크입니다"));
    }

    public static List<Rank> findRanksBetween(final Rank from, final Rank to) {
        final int min = Math.min(from.position, to.position);
        final int max = Math.max(from.position, to.position);
        List<Rank> ranks = Arrays.stream(values())
                .filter(rank -> rank.position > min && rank.position < max)
                .collect(Collectors.toList());

        if (from.position == max) {
            Collections.reverse(ranks);
        }
        return ranks;
    }

    public int calculateDistanceTo(final Rank other) {
        return Math.abs(position - other.position);
    }

    public boolean isBiggerThan(final Rank other) {
        return this.position > other.position;
    }

    public int getPosition() {
        return position;
    }
}
