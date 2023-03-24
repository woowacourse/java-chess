package chess.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Score {

    private static final Map<Double, Score> CACHE = new HashMap<>();
    private static final int PAWN_REDUCE_COUNT_LIMIT = 1;
    private static final Score PAWN_REDUCE_SCORE = Score.from(0.5);
    public static final Score INITIAL_SCORE = Score.from(0);

    private final double value;

    private Score(final double value) {
        this.value = value;
    }

    public static Score from(final double value) {
        return CACHE.computeIfAbsent(value, k -> new Score(value));
    }

    public Score add(final Score other) {
        return Score.from(this.value + other.value);
    }

    public Score reducePawnScore(final long pawnCount) {
        if (pawnCount > PAWN_REDUCE_COUNT_LIMIT) {
            return this.subtract(PAWN_REDUCE_SCORE.multiply(pawnCount));
        }
        return this;
    }

    private Score multiply(final long count) {
        return Score.from(this.value * count);
    }

    private Score subtract(final Score other) {
        return Score.from(this.value - other.value);
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
