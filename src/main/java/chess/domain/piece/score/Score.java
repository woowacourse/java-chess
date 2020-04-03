package chess.domain.piece.score;

import java.util.Objects;

public class Score {
    private final double value;

    //todo: add validation logic
    public Score(double value) {
        this.value = value;
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

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public boolean isBiggerThan(Score other) {
        return other.value < value;
    }
}
