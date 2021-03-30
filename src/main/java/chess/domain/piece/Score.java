package chess.domain.piece;

import java.util.Objects;

public class Score {

    public static final Score ZERO_SCORE = new Score(0);
    private static final double PAWN_PENALTY_SCORE = 0.5d;

    private final double value;

    public Score(final double value) {
        this.value = value;
    }

    public Score sum(final Score score) {
        return new Score(this.value + score.value);
    }

    public double value() {
        return this.value;
    }

    public Score calculatePawnPenaltyScore(final int pawnCountInLine) {
        return new Score(this.value - (PAWN_PENALTY_SCORE * pawnCountInLine));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return Double.compare(score.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
