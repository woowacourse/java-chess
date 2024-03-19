package chess;

import java.util.Objects;

public class RankDifference {

    private final int difference;

    public RankDifference(int difference) {
        this.difference = difference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RankDifference that = (RankDifference) o;
        return difference == that.difference;
    }

    @Override
    public int hashCode() {
        return Objects.hash(difference);
    }
}
