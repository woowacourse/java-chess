package domain.position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Rank {

    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    ;

    private final int order;

    Rank(int order) {
        this.order = order;
    }

    public static List<Rank> reversedValues() {
        List<Rank> ranks = new ArrayList<>(List.of(values()));
        Collections.reverse(ranks);
        return ranks;
    }

    public int distance(Rank target) {
        return Math.abs(order - target.order);
    }

    public boolean isUp(Rank target) {
        return order - target.order < 0;
    }

    public boolean isDown(Rank target) {
        return order - target.order > 0;
    }

    public List<Rank> betweenRanks(Rank target) {
        int sourceOrder = order;
        int targetOrder = target.order;
        if (sourceOrder < targetOrder) {
            return makeBetweenRanks(sourceOrder, targetOrder);
        }
        List<Rank> ranks = makeBetweenRanks(targetOrder, sourceOrder);
        Collections.reverse(ranks);
        return ranks;
    }

    private List<Rank> makeBetweenRanks(int from, int to) {
        return new ArrayList<>(Arrays.stream(values())
                .filter(rank -> isBetween(rank.order, from, to))
                .toList());
    }

    private boolean isBetween(int number, int from, int to) {
        return number > from && number < to;
    }
}
