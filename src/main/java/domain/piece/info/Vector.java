package domain.piece.info;

public class Vector {
    private final int fileDifference;
    private final int rankDifference;

    public Vector(final Position source, final Position target) {
        final int fileDifference = source.fileIndex() - target.fileIndex();
        final int rankDifference = source.fileIndex() - target.rankIndex();
        if (fileDifference == 0 && rankDifference == 0) {
            throw new IllegalArgumentException("두 위치가 같습니다");
        }
        this.fileDifference = fileDifference;
        this.rankDifference = rankDifference;
    }

    public boolean isStraight() {
        return fileDifference == 0 || rankDifference == 0;
    }

    public boolean isDiagonal() {
        return Math.abs(fileDifference) == Math.abs(rankDifference);
    }


}
