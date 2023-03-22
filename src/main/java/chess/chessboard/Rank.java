package chess.chessboard;

import java.util.*;
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

    public static List<Rank> ranksBetween(final Rank source, final Rank destination) {
        final int smaller = Math.min(source.position, destination.position);
        final int bigger = Math.max(source.position, destination.position);

        List<Rank> ranks = ranksBetween(smaller, bigger);

        return sortByPosition(source, destination, ranks);
    }

    private static List<Rank> ranksBetween(final int smaller, final int bigger) {
        return Arrays.stream(values())
                     .filter(rank -> rank.position > smaller && rank.position < bigger)
                     .collect(Collectors.toList());
    }

    private static List<Rank> sortByPosition(final Rank source, final Rank destination, final List<Rank> ranks) {
        final Comparator<Rank> comparatorByPosition = Comparator.comparing(Rank::getPosition);

        ranks.sort(comparatorByPosition);
        if (source.position > destination.position) {
            Collections.reverse(ranks);
        }

        return ranks;
    }

    public int distanceTo(final Rank other) {
        return Math.abs(position - other.position);
    }

    public boolean isBiggerThan(final Rank other) {
        return this.position > other.position;
    }

    public int getPosition() {
        return position;
    }

}
