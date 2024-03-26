package chess.score;

import java.util.Objects;

public class Score {
    private final double score;

    public Score(double score) {
        this.score = score;
    }

    public Score add(Score other) {
        return new Score(this.score + other.score);
    }

    public Score subtract(double subtrahend) {
        return new Score(this.score - subtrahend);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Score score1 = (Score) o;
        return Double.compare(score, score1.score) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(score);
    }
}
