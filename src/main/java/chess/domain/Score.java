package chess.domain;

import java.util.Objects;

public class Score {
    private final double value;

    public Score(double value) {
        this.value = value;
    }

    public Score sum(Score target) {
        return new Score(this.value + target.value);
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
