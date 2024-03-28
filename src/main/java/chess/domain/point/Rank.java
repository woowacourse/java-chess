package chess.domain.point;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Rank {

    FIRST(1),
    SECOND(2),
    THIRD(3),
    FOURTH(4),
    FIFTH(5),
    SIXTH(6),
    SEVENTH(7),
    EIGHTH(8);

    private static final Map<Integer, Rank> POOL;

    static {
        POOL = Arrays.stream(Rank.values())
                .collect(Collectors.toMap(rank -> rank.rank, Function.identity()));
    }

    private final int rank;

    Rank(int rank) {
        this.rank = rank;
    }

    public static Rank of(int rank) {
        if (!POOL.containsKey(rank)) {
            throw new IllegalArgumentException(String.format("세로 위치는 %d ~ %d 사이의 값이어야 합니다.", minValue(), maxValue()));
        }
        return POOL.get(rank);
    }

    public int distance(Rank rank) {
        return this.rank - rank.rank;
    }

    public Rank add(int directionOfRank) {
        return Rank.of(rank + directionOfRank);
    }

    public boolean addable(int addRank) {
        int addedRank = this.rank + addRank;

        return addedRank >= minValue() && addedRank <= maxValue();
    }

    public static int maxValue() {
        return EIGHTH.rank;
    }

    public static int minValue() {
        return FIRST.rank;
    }
}
