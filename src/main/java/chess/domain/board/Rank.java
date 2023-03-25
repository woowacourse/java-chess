package chess.domain.board;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public enum Rank {
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1);

    private final int value;

    Rank(final int value) {
        this.value = value;
    }

    public int calculateDistance(final Rank otherRank) {
        return Math.abs(otherRank.value - value);
    }

    public int calculateDifference(final Rank otherRank) {
        return otherRank.value - value;
    }

    public List<Rank> getRanksInRange(final Rank otherRank) {
        final int max = Math.max(value, otherRank.value);
        final int min = Math.min(value, otherRank.value);
        final List<Rank> ranks = Arrays.stream(values())
                .filter(file -> file.value > min && file.value < max)
                .collect(Collectors.toList());
        if (value < otherRank.value) {
            Collections.reverse(ranks);
        }
        return ranks;
    }

}
