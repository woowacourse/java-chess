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

    public int calculateDifferenceTo(Rank rank) {
        return rank.value - value;
    }

    public List<Rank> findRankRouteToTargetRank(Rank to) {
        if (to.value > value) {
            return inclineDirectionRoute(to);
        }
        return declineDirectionRoute(to);
    }

    private List<Rank> inclineDirectionRoute(Rank to) {
        List<Rank> ranks = new ArrayList<>();
        for (int i = value + 1; i < to.value; i++) {
            ranks.add(Rank.of(i));
        }
        return ranks;
    }

    private List<Rank> declineDirectionRoute(Rank to) {
        List<Rank> ranks = new ArrayList<>();
        for (int i = value - 1; i > to.value; i--) {
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

    public static Rank of(String name) {
        return of(Integer.parseInt(name));
    }
}
