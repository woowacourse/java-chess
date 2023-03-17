package chessgame.domain.point;

import java.util.Arrays;
import java.util.Optional;

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

    Rank(int value) {
        this.value = value;
    }

    public static Optional<Rank> findRank(int result) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.value == result)
                .findFirst();
    }

    public int distance(Rank target) {
        return this.value - target.value;
    }

    public Optional<Rank> move(int rankMove) {
        int result = value + rankMove;

        return findRank(result);
    }
}
