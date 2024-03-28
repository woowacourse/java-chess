package domain;

import java.util.Objects;

public class Score implements Comparable<Score> {

    private final double value;

    public Score(final double value) {
        this.value = value;
    }

    public Score sum(final Score other) {
        return new Score(this.value + other.value);
    }

    public Score subtract(final Score other) {
        return new Score(this.value - other.value);
    }

    public Score multiply(final int count) {
        return new Score(value * count);
    }

    public double toDouble() {
        return value;
    }

    @Override
    public int compareTo(final Score o) {
        return Double.compare(this.value, o.value);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Score score = (Score) o;
        return Double.compare(value, score.value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Score{" +
                "value=" + value +
                '}';
    }
}
