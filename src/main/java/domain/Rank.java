package domain;

import java.util.Arrays;
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

    public static int max() {
        return Arrays.stream(values())
                .mapToInt(it -> it.order)
                .max()
                .orElseThrow();
    }

    public static Rank find(int order) {
        return Arrays.stream(values())
                .filter(it -> it.order == order)
                .findFirst()
                .orElseThrow();
    }

    public boolean confirmGap(Rank other, int gapSize) {
        return gap(other) == gapSize;
    }

    public int gap(Rank other) {
        int otherOrder = other.order;
        return Math.abs(order - otherOrder);
    }

    public boolean isBigger(Rank other) {
        return order > other.order;
    }

    public boolean isLess(Rank other) {
        return order < other.order;
    }

    public List<Rank> findBetween(Rank target) {
        if (this.order > target.order) {
            return Arrays.stream(values())
                    .filter(rank -> rank.order < this.order && rank.order > target.order)
                    .toList();
        }
        return Arrays.stream(values())
                .filter(rank -> rank.order > this.order && rank.order < target.order)
                .toList();
    }
}
