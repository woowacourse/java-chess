package chess.domain.piece.score;

import java.util.Objects;

public class Score {
    private final double value;

    private Score(double value) {
        this.value = value;
    }

    public static Score zero() {
        return new Score(0);
    }

    public static Score of(double value) {
        return new Score(value);
    }

    public Score add(Score other) {
        return new Score(value + other.value);
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
}
