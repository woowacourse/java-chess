package domain.board;

import java.util.Objects;

public final class Rank {

    private final int value;

    public Rank(int value) {
        this.value = value;
    }

    public int subtract(Rank otherRank) {
        return this.value - otherRank.value;
    }

    public boolean isRankTwo() {
        return value == 2;
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
