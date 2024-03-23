package domain.board;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class Rank {

    private static final Map<Integer, Rank> CACHE = new HashMap<>();
    private final int value;

    private Rank(int value) {
        this.value = value;
    }

    public static Rank valueOf(int value) {
        if (CACHE.containsKey(value)) {
            return CACHE.get(value);
        }
        CACHE.put(value, new Rank(value));
        return CACHE.get(value);
    }

    public int subtract(Rank otherRank) {
        return this.value - otherRank.value;
    }

    public boolean isRankTwo() {
        return value == 2;
    }

    public boolean isRankSeven() {
        return value == 7;
    }

    public int value() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        var that = (Rank) obj;
        return this.value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Rank[" +
                "value=" + value + ']';
    }
}
