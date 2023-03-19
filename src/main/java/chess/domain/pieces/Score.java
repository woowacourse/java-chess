package chess.domain.pieces;

import java.util.Objects;

public class Score {

    private final double score;

    public Score(final double score) {
        this.score = score;
    }

    public double getScore() {
        return this.score;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Score)) {
            return false;
        }
        Score score1 = (Score) o;
        return Double.compare(score1.score, score) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
