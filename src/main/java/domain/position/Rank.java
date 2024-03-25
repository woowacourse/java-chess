package domain.position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum Rank {

    ONE(8),
    TWO(7),
    THREE(6),
    FOUR(5),
    FIVE(4),
    SIX(3),
    SEVEN(2),
    EIGHT(1);

    private final int order;

    Rank(int order) {
        this.order = order;
    }

    public static List<Rank> valuesByOrder() {
        List<Rank> ranks = new ArrayList<>(List.of(Rank.values()));
        ranks.sort(Comparator.comparingInt(o -> o.order));
        return ranks;
    }

    public int distance(Rank target) {
        return Math.abs(this.order - target.order);
    }

    public int forwardDistance(Rank target) {
        return this.order - target.order;
    }

    public List<Rank> betweenRanks(Rank target) {
        List<Rank> ranks = valuesByOrder();
        int sourceOrder = this.order;
        int targetOrder = target.order;
        if (sourceOrder < targetOrder) {
            return ranks.subList(sourceOrder, targetOrder - 1);
        }
        List<Rank> betweenRanks = new ArrayList<>(ranks.subList(targetOrder, sourceOrder - 1));
        Collections.reverse(betweenRanks);
        return Collections.unmodifiableList(betweenRanks);
    }

    public boolean isSame(Rank rank) {
        return this == rank;
    }
}
