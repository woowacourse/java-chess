package chess.domain.score;

import java.util.Objects;

public class Score {

    private static final String INVALID_SCORE = "점수는 음수일 수 없습니다.";
    private final double value;

    private Score(double score) {
        validate(score);
        this.value = score;
    }

    public static Score of(double score) {
        return new Score(score);
    }

    private void validate(double score) {
        if (score < 0) {
            throw new IllegalStateException(INVALID_SCORE);
        }
    }

    public Score half() {
        return new Score(value / 2);
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Score score = (Score) o;
        return Double.compare(value, score.value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
