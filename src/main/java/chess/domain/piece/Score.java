package chess.domain.piece;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Score {
    private static final Map<Double, Score> CACHE = new HashMap<>();

    private final double score;

    private Score(final double score) {
        this.score = score;
    }

    public static Score create(final double score) {
        if (!CACHE.containsKey(score)) {
            CACHE.put(score, new Score(score));
        }
        return CACHE.get(score);
    }

    public Score add(final Score other) {
        return new Score(score + other.score);
    }

    public Score subtract(final Score other) {
        return new Score(score - other.score);
    }

    public Score multiply(final long count) {
        return new Score(score * count);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Score score1 = (Score) o;
        return Double.compare(score1.score, score) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }

    public double getScore() {
        return score;
    }
}
