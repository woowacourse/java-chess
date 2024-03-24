package domain.position;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Rank {
    ONE(7),
    TWO(6),
    THREE(5),
    FOUR(4),
    FIVE(3),
    SIX(2),
    SEVEN(1),
    EIGHT(0);

    private static final Map<Integer, Rank> RANK_POOL = new HashMap<>();

    static {
        Arrays.stream(values()).forEach(rank -> RANK_POOL.put(rank.index, rank));
    }

    private final int index;

    Rank(final int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public static Rank of(final int index) {
        if (!RANK_POOL.containsKey(index)) {
            throw new IllegalArgumentException("유효하지 않은 인덱스입니다.");
        }
        return RANK_POOL.get(index);
    }
}
