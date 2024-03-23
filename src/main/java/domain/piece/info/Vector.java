package domain.piece.info;

public class Vector {
    private final int fileDifference;
    private final int rankDifference;

    public Vector(final Position source, final Position target) {
        final int fileDifference = target.fileIndex() - source.fileIndex();
        final int rankDifference = target.rankIndex() - source.rankIndex();
        validateZeroVector(fileDifference, rankDifference);

        this.fileDifference = fileDifference;
        this.rankDifference = rankDifference;
    }

    private void validateZeroVector(final int fileDifference, final int rankDifference) {
        if (fileDifference == 0 && rankDifference == 0) {
            throw new IllegalArgumentException("두 위치가 같습니다");
        }
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
}
