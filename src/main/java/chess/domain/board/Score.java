package chess.domain.board;

import java.math.BigDecimal;
import java.util.Objects;

public class Score {

    private final BigDecimal score;

    public Score(final BigDecimal score) {
        this.score = score;
    }

    public Score add(Score scoreToAdd) {
        return new Score(this.score.add(scoreToAdd.score));
    }

    public Score subtract(Score scoreToSubtract) {
        return new Score(this.score.subtract(scoreToSubtract.score));
    }

    public boolean isGreaterScore(Score scoreToCompare) {
        return this.score.compareTo(scoreToCompare.score) > 0;
    }

    public boolean isLessScore(Score scoreToCompare) {
        return this.score.compareTo(scoreToCompare.score) < 0;
    }

    public boolean isEqualScore(Score scoreToCompare) {
        return this.score.compareTo(scoreToCompare.score) == 0;
    }

    public BigDecimal getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return Objects.equals(score, score1.score);
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
