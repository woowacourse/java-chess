package chess.domain.board;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public enum Rank {
    EIGHT("8", 8),
    SEVEN("7", 7),
    SIX("6", 6),
    FIVE("5", 5),
    FOUR("4", 4),
    THREE("3", 3),
    TWO("2", 2),
    ONE("1", 1),
    ;

    private static final String INVALID_RANGE = "유효하지 않은 범위입니다.";

    private final String value;
    private final int order;

    Rank(String value, int order) {
        this.value = value;
        this.order = order;
    }

    public static Rank of(String value) {
        return Arrays.stream(values())
                .filter(rank -> rank.value.equals(value))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException(INVALID_RANGE));
    }

    public static List<Rank> initialRows() {
        return List.of(EIGHT, SEVEN, TWO, ONE);
    }

    public static List<Rank> getBetween(Rank from, Rank to) {
        Rank maxOrder = getHigherOrder(from, to);
        Rank minOrder = getLowerOrder(from, to);

        List<Rank> ranks = Arrays.stream(values())
                .filter(column -> column.order < maxOrder.order && column.order > minOrder.order)
                .collect(Collectors.toList());

        if (minOrder == from) {
            return reverseRanks(ranks);
        }

        return ranks;
    }

    private static List<Rank> reverseRanks(List<Rank> ranks) {
        return ranks.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    private static Rank getHigherOrder(Rank from, Rank to) {
        if (from.order > to.order) {
            return from;
        }
        return to;
    }

    private static Rank getLowerOrder(Rank from, Rank to) {
        if (from.order < to.order) {
            return from;
        }
        return to;
    }

    public int calculate(Rank other) {
        return other.order - this.order;
    }
}
