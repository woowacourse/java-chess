package domain.piece.info;

import java.util.Objects;

public class Vector {
    private final int fileDifference;
    private final int rankDifference;

    private Vector(final int fileDifference, final int rankDifference) {
        this.fileDifference = fileDifference;
        this.rankDifference = rankDifference;
    }

    public Vector(final Position source, final Position target) {
        final int fileDifference = target.toFileIndex() - source.toFileIndex();
        final int rankDifference = target.toRankIndex() - source.toRankIndex();
        validateZeroVector(fileDifference, rankDifference);

        this.fileDifference = fileDifference;
        this.rankDifference = rankDifference;
    }


    public static Vector of(final int fileDifference, final int rankDifference) {
        return new Vector(fileDifference, rankDifference);
    }

    private void validateZeroVector(final int fileDifference, final int rankDifference) {
        if (fileDifference == 0 && rankDifference == 0) {
            throw new IllegalArgumentException("두 위치가 같습니다");
        }
    }

    public Vector inverse() {
        return new Vector(-fileDifference, -rankDifference);
    }

    public boolean isStraight() {
        return fileDifference == 0 || rankDifference == 0;
    }

    public boolean isDiagonal() {
        return Math.abs(fileDifference) == Math.abs(rankDifference);
    }

    public boolean isUnitVector() {
        return Math.abs(fileDifference) == 1 || Math.abs(rankDifference) == 1;
    }

    public int absoluteSum() {
        return Math.abs(fileDifference) + Math.abs(rankDifference);
    }

    public boolean hasAbsoluteValueOf(final int value) {
        return Math.abs(fileDifference) == value || Math.abs(rankDifference) == value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Vector vector = (Vector) o;
        return fileDifference == vector.fileDifference && rankDifference == vector.rankDifference;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileDifference, rankDifference);
    }
}
