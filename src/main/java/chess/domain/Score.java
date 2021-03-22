package chess.domain;

import java.util.Objects;

public final class Score {

    private static final double PAWN_EXCEPT_SCORE = 0.5;

    private final double value;

    public Score(double value) {
        this.value = value;
    }

    public static Score from(double value) {
        return new Score(value);
    }

    public Score add(Score score) {
        return new Score(value + score.value);
    }

    public Score minusPawnCount(int pawnCount) {
        return new Score(value - (PAWN_EXCEPT_SCORE * pawnCount));
    }

    public boolean greaterThan(Score score) {
        return value > score.value;
    }

    public double value() {
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

    @Override
    public String toString() {
        return "Score{" +
            "value=" + value +
            '}';
    }
}
