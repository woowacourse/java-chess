package domain.position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

    public List<Rank> betweenRanks(Rank target) {
        List<Rank> ranks = Arrays.stream(values()).toList();
        int sourceIndex = ranks.indexOf(this);
        int targetIndex = ranks.indexOf(target);
        if (sourceIndex < targetIndex) {
            return ranks.subList(sourceIndex + 1, targetIndex);
        }
        List<Rank> betweenRanks = new ArrayList<>(ranks.subList(targetIndex + 1, sourceIndex));
        Collections.reverse(betweenRanks);
        return Collections.unmodifiableList(betweenRanks);
    }

    public boolean isSame(Rank rank) {
        return this == rank;
    }
}
