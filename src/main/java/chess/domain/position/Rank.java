package chess.domain.position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Rank {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public RankDifference calculateDifference(Rank rank) {
        return new RankDifference(rank.value - value);
    }

    public List<Rank> getRankRoute(Rank to) {
        List<Rank> ranks = new ArrayList<>();
        if (value == to.value) {
            ranks.add(this);
            return ranks;
        }

        int unitDirection = (to.value - value) / Math.abs(to.value - value);
        for (int i = value + unitDirection; i != to.value; i += unitDirection) {
            ranks.add(Rank.of(i));
        }
        return ranks;
    }

    public static Rank of(int value) {
        return Arrays.stream(values())
                .filter(rank -> rank.value == value)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
