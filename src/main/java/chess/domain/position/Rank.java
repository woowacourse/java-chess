package chess.domain.position;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum Rank {

    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1),
    ;

    private final int order;

    Rank(int order) {
        this.order = order;
    }

    public int gap(Rank other) {
        return Math.abs(difference(other));
    }

    public int difference(Rank other) {
        return order - other.order;
    }

    public List<Rank> findBetween(Rank other) {
        if (this.order < other.order) {
            return betweenDesc(other);
        }
        return betweenAsc(other);
    }

    private List<Rank> betweenAsc(Rank other) {
        return Arrays.stream(values())
                .filter(file -> file.order > other.order && file.order < this.order)
                .toList();
    }

    private List<Rank> betweenDesc(Rank other) {
        return Arrays.stream(values())
                .filter(file -> file.order > this.order && file.order < other.order)
                .sorted(Comparator.comparingInt(Rank::ordinal).reversed())
                .toList();
    }
}
