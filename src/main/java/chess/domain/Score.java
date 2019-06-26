package chess.domain;

import java.util.Objects;

public class Score {
    private static final int ROUNDING_CRITERION = 10;

    private final double score;

    public Score(final double score) {
        this.score = round(score);
    }

    private double round(double score) {
        return Math.round(score * ROUNDING_CRITERION) / (double) ROUNDING_CRITERION;
    }

    public double getScore() {
        return score;
    }

    public Score add(Score score) {
        return new Score(this.score + score.score);
    }

    public Boolean isHigher(final Score score) {
        return this.score > score.score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return Double.compare(score1.score, score) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }

    @Override
    public String toString() {
        return "Score{" +
                "score=" + score +
                '}';
    }
}
