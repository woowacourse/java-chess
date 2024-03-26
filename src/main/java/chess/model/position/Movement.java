package chess.model.position;

import chess.model.piece.Side;

import java.util.Objects;

public class Movement {
    private final Difference fileDifference;
    private final Difference rankDifference;

    public Movement(Difference fileDifference, Difference rankDifference) {
        this.fileDifference = fileDifference;
        this.rankDifference = rankDifference;
    }

    public boolean isForward(boolean isUpperSide) {
        if (!fileDifference.isZero()) {
            return false;
        }
        if (isUpperSide) {
            return rankDifference.isNegative();
        }
        return rankDifference.isPositive();
    }

    public boolean isDiagonal() {
        if (isNotMoved()) {
            return false;
        }
        return fileDifference.absoluteValue() == rankDifference.absoluteValue();
    }

    public boolean isOrthogonal() {
        if (isNotMoved()) {
            return false;
        }
        return fileDifference.isZero() || rankDifference.isZero();
    }

    private boolean isNotMoved() {
        return fileDifference.isZero() && rankDifference.isZero();
    }

    public boolean hasLengthOf(int displacement) {
        return calculateLength() == displacement;
    }

    public int calculateLength() {
        if (isNotMoved()) {
            return 0;
        }
        if (!isOrthogonal() && !isDiagonal()) {
            return fileDifference.plusByAbsoluteValue(rankDifference);
        }
        if (fileDifference.isZero()) {
            return rankDifference.absoluteValue();
        }
        return fileDifference.absoluteValue();
    }

    public int calculateFileIncrement() {
        return calculateIncrement(fileDifference);
    }

    public int calculateRankIncrement() {
        return calculateIncrement(rankDifference);
    }

    private int calculateIncrement(Difference difference) {
        if (difference.isNegative()) {
            return -1;
        }
        if (difference.isZero()) {
            return 0;
        }
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movement movement = (Movement) o;
        return Objects.equals(fileDifference, movement.fileDifference)
                && Objects.equals(rankDifference, movement.rankDifference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileDifference, rankDifference);
    }
}
