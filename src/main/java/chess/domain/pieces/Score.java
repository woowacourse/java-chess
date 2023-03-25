package chess.domain.pieces;

import java.math.BigDecimal;
import java.util.Objects;

public class Score {

    private final BigDecimal score;

    public Score(final int score) {
        this.score = new BigDecimal(String.valueOf(score));
    }

    public Score(final BigDecimal score) {
        this.score = score;
    }

    public Score(final Double score) {
        this.score = new BigDecimal(String.valueOf(score));
    }

    public Score(final String score) {
        this.score = new BigDecimal(score);
    }

    public Score add(final Score otherScore) {
        return new Score(this.score.add(otherScore.score));
    }

    public BigDecimal getScore() {
        return score;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return Objects.equals(score, score1.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
