package chessgame.domain.point;

import java.util.HashMap;
import java.util.Map;

public enum Rank {
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1);
    private static final Map<Integer, Rank> RANK_MAP = new HashMap<>();

    static {
        for (Rank rank : values()) {
            RANK_MAP.put(rank.value, rank);
        }
    }

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public static Rank findRank(int result) {
        if (RANK_MAP.containsKey(result)) {
            return RANK_MAP.get(result);
        }
        throw new IllegalArgumentException("rank 좌표가 잘못되었습니다.");
    }

    public int distance(Rank target) {
        return this.value - target.value;
    }

    public Rank move(int rankMove) {
        int result = value + rankMove;

        return findRank(result);
    }
}
