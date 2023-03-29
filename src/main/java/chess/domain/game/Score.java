package chess.domain.game;

import java.math.BigDecimal;
import java.util.Objects;

public class Score {

    private final BigDecimal value;

    private Score(final BigDecimal value) {
        this.value = value;
    }

    public static Score from(final double value) {
        return new Score(BigDecimal.valueOf(value));
    }

    public Score add(final Score score) {
        return new Score(value.add(score.value));
    }

    public Score minus(final Score score) {
        return new Score(value.subtract(score.value));
    }

    public double value() {
        return value.doubleValue();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Score score = (Score) o;
        return Objects.equals(value, score.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
