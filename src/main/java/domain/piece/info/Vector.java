package domain.piece.info;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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

    public Vector reflectHorizontally() {
        return new Vector(fileDifference, -rankDifference);
    }

    public boolean isStraight() {
        return fileDifference == 0 || rankDifference == 0;
    }

    public boolean isDiagonal() {
        return Math.abs(fileDifference) == Math.abs(rankDifference);
    }

    public boolean isStraightOrDiagonal() {
        return isStraight() || isDiagonal();
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

    public boolean hasAbsoluteValueMoreOrEqualThan(final int value) {
        return Math.abs(fileDifference) >= value || Math.abs(rankDifference) >= value;
    }

    public List<Position> generatePathExcludingEndpoints(final Position source) {
        final int maxDifferecne = maxAbsoluteValue();
        final int unitFile = fileDifference / maxDifferecne;
        final int unitRank = rankDifference / maxDifferecne;
        final LinkedList<Position> ret = new LinkedList<>();
        ret.add(source);
        for (int counter = 0; counter < maxDifferecne; counter++) {
            ret.add(ret.getLast().next(unitFile, unitRank));
        }
        ret.removeLast();
        ret.removeFirst();
        return ret;
    }

    private int maxAbsoluteValue() {
        return Math.max(Math.abs(fileDifference), Math.abs(rankDifference));
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
