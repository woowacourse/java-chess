package chess.domain.piece;

import java.util.Objects;

public class Score {

    public static final Score ZERO_SCORE = new Score(0);
    public static final Score PAWN_SCORE = new Score(1.0d);
    public static final Score KNIGHT_SCORE = new Score(2.5d);
    public static final Score BISHOP_SCORE = new Score(3.0d);
    public static final Score ROOK_SCORE = new Score(5.0d);
    public static final Score QUEEN_SCORE = new Score(9.0d);
    private static final double PAWN_PENALTY_SCORE = 0.5d;

    private final double value;

    public Score(final double value) {
        this.value = value;
    }

    public Score sum(final Score score) {
        return new Score(this.value + score.value);
    }

    public Score calculatePawnPenaltyScore(final int pawnCountInLine) {
        return new Score(this.value - (PAWN_PENALTY_SCORE * pawnCountInLine));
    }

    public boolean isHigherThan(final Score other) {
        return this.value > other.value;
    }

    public double value() {
        return this.value;
    }

    @Override
    public boolean equals(final Object o) {
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
