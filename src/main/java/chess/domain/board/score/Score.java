package chess.domain.board.score;

import java.math.BigDecimal;
import java.util.Objects;

public class Score {

    public static final Score ZERO = new Score(BigDecimal.ZERO);

    private final BigDecimal value;

    private Score(final BigDecimal value) {
        this.value = value;
    }

    public static Score from(final double value) {
        return new Score(BigDecimal.valueOf(value));
    }

    public Score plus(final Score score) {
        return new Score(value.add(score.value));
    }

    public Score multiply(final int repeat) {
        return new Score(value.multiply(BigDecimal.valueOf(repeat)));
    }

    public BigDecimal value() {
        return value;
    }

    public boolean isGreaterThan(final Score other) {
        return value.compareTo(other.value) > 0;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Score score = (Score) o;
        return score.value.compareTo(value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
