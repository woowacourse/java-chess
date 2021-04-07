package domain.board;

import java.io.Serializable;
import java.util.Objects;

public class Score implements Serializable {

    private final double value;

    public Score() {
        value = 0;
    }

    public Score(double value) {
        this.value = value;
    }

    public Score sum(Score score) {
        return new Score(this.value + score.value);
    }

    public Score minusPawnScore(int count) {
        if (count == 0) {
            return this;
        }
        return new Score(value - (count * 0.5));
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Score score = (Score) o;
        return Double.compare(score.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
