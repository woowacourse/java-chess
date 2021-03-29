package chess.domain.piece;

import java.util.Objects;

public class Score {
    public static final Score EMPTY = new Score(0.0d);
    public static final Score PAWN = new Score(1.0d);
    public static final Score KNIGHT = new Score(2.5d);
    public static final Score BISHOP = new Score(3.0d);
    public static final Score QUEEN = new Score(9.0d);
    public static final Score ROOK = new Score(5.0d);
    private static final Score PAWN_PENALTY_SCORE = new Score(0.5d);

    private final double value;

    public Score(final double value) {
        this.value = value;
    }

    public Score plus(final Score score) {
        return new Score(this.value + score.value);
    }

    public double score() {
        return this.value;
    }

    public Score applyPawnPenalty(final int pawnCountInLine) {
        return new Score(this.value - (PAWN_PENALTY_SCORE.value * pawnCountInLine));
    }

    public Score bigger(final Score score) {
        return new Score(Math.max(this.value, score.value));
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
