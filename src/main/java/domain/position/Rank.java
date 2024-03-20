package domain.position;

import java.util.Arrays;
import java.util.List;

public enum Rank {

    EIGHT, SEVEN, SIX, FIVE, FOUR, THREE, TWO, ONE;

    public int distance(Rank target) {
        List<Rank> ranks = Arrays.stream(values()).toList();
        int sourceIndex = ranks.indexOf(this);
        int targetIndex = ranks.indexOf(target);
        return Math.abs(sourceIndex - targetIndex);
    }

    public int forwardDistance(Rank target) {
        List<Rank> ranks = Arrays.stream(values()).toList();
        int sourceIndex = ranks.indexOf(this);
        int targetIndex = ranks.indexOf(target);
        return sourceIndex - targetIndex;
    }

    public boolean isSame(Rank rank) {
        return this == rank;
    }
}
