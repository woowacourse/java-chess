package chess.score;

import java.util.Objects;

public class Score {
    private final double value;

    public Score(double value) {
        validNegative(value);
        this.value = value;
    }

    private void validNegative(double value) {
        if (value < 0) {
            throw new NegativeScoreCreateException();
        }
    }

    public Score plus(Score score) {
        return new Score(this.value + score.value);
    }

    public Score minus(Score score) {
        return new Score(this.value - score.value);
    }

    public boolean isHigherThan(Score score2) {
        return value > score2.value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return Double.compare(score.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
