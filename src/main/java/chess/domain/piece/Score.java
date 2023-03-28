package chess.domain.piece;

import java.util.HashMap;
import java.util.Map;

public final class Score {

    private static final Map<Double, Score> cache = new HashMap<>();
    private static final int MIN_SCORE = 0;
    private final double score;

    private Score(final double score) {
        validateScore(score);
        this.score = score;
    }

    private void validateScore(final double score) {
        if (score < MIN_SCORE) {
            throw new IllegalArgumentException("점수는 " + MIN_SCORE + "점보다 작을 수 없습니다.");
        }
    }

    public static Score from(final double score) {
        return cache.computeIfAbsent(score, key -> new Score(score));
    }

    public Score add(final Score other) {
        return Score.from(score + other.score);
    }

    public Score subtract(final Score other) {
        return Score.from(score - other.score);
    }

    public Score multiply(final double ratio) {
        return Score.from(score * ratio);
    }

    public double getScore() {
        return this.score;
    }
}
