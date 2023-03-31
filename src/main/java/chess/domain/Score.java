package chess.domain;

import java.util.Objects;

public final class Score {

    private static final Score init = new Score(0);

    private final double value;

    public Score(final double value) {
        this.value = value;
    }

    public static Score init() {
        return init;
    }

    public Score plus(final Score score) {
        return new Score(value + score.value);
    }

    public Score minus(final Score score) {
        return new Score(value - score.value);
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Score)) return false;
        Score score = (Score) o;
        return Double.compare(score.value, value) == 0;
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
