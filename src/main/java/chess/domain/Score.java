package chess.domain;

import java.util.Objects;

public class Score {

    private static final Score ZERO_SCORE = new Score(0);

    private final double value;

    private Score(double value) {
        this.value = value;
    }

    public static Score from(double value) {
        if (value == 0) {
            return ZERO_SCORE;
        }
        return new Score(value);
    }

    public Score add(Score score) {
        return from(this.value + score.value);
    }

    public Score minus(Score score) {
        return Score.from(value - score.value);
    }

    public double value() {
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
