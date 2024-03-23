package chess.domain.position;

import java.util.Objects;

public abstract class Difference {

    private final int difference;

    protected Difference(int difference) {
        this.difference = difference;
    }

    public boolean hasDistance(int distance) {
        if (distance < 0) {
            throw new IllegalArgumentException("음수 거리를 비교할 수 없습니다");
        }
        return Math.abs(difference) == distance;
    }

    public boolean hasSameDistance(Difference otherDifference) {
        return otherDifference.hasDistance(Math.abs(difference));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Difference that = (Difference) o;
        return difference == that.difference;
    }

    @Override
    public int hashCode() {
        return Objects.hash(difference);
    }
}
