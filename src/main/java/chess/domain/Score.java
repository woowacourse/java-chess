package chess.domain;


import java.util.Objects;

public class Score {

    private final double value;

    private Score(final double value) {
        this.value = value;
    }

    public static Score from(final double value) {
        return new Score(value);
    }

    public Score subtract(final Score other) {
        return new Score(this.value - other.value);
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return value == score.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
