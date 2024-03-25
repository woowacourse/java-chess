package chess.domain.vo;

import java.util.Objects;

public class Score {
    public static final int MIN_SCORE_VALUE = 0;

    private final double score;

    public Score(double score) {
        validate(score);
        this.score = score;
    }

    public boolean isGreaterThan(Score otherScore) {
        return this.score > otherScore.score;
    }

    public Score diminishScore(Score diminishUnit, double diminishCount) {
        return new Score(score - diminishUnit.score * diminishCount);
    }

    public double getValue() {
        return score;
    }

    private void validate(double score) {
        if (score < MIN_SCORE_VALUE) {
            throw new IllegalArgumentException("점수는 " + MIN_SCORE_VALUE + "점 이상이어야합니다.");
        }
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
        return Objects.hash(score);
    }
}
