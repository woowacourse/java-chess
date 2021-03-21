package domain;

import java.util.Objects;

public class Score {

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

    public Score half() {
        return new Score(value / 2);
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
