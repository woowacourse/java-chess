package domain.position;

import java.util.Objects;

public class Position {
    private final File file;
    private final Rank rank;

    public Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public boolean isUpperRankThan(final Position target) {
        return this.rank.isUpperThan(target.rank);
    }

    public boolean isLowerRankThan(final Position target) {
        return this.rank.isLowerThan(target.rank);
    }

    public boolean isStraight(final Position target) {
        validateSamePosition(target);
        return calculateFileGap(target) == 0;
    }

    public boolean isDiagonal(final Position target) {
        validateSamePosition(target);
        return calculateFileGap(target) == calculateRankGap(target);
    }

    boolean isLShape(final Position target) {
        validateSamePosition(target);
        return calculateFileGap(target) * calculateRankGap(target) == 2;
    }

    boolean isAdjacent(final Position target) {
        validateSamePosition(target);
        return calculateFileGap(target) == 0 && calculateRankGap(target) == 0;
    }

    boolean isVertical(final Position target) {
        validateSamePosition(target);
        return calculateFileGap(target) == 0 && calculateRankGap(target) > 0;
    }

    boolean isHorizontal(final Position target) {
        validateSamePosition(target);
        return calculateFileGap(target) > 0 && calculateRankGap(target) == 0;
    }

    private void validateSamePosition(final Position target) {
        if (this.equals(target)) {
            throw new IllegalArgumentException("동일한 위치입니다.");
        }
    }

    public int calculateRankGap(final Position target) {
        return Math.abs(rank.subtract(target.rank));
    }

    public int calculateFileGap(final Position target) {
        return Math.abs(file.subtract(target.file));
    }

    public int calculateDistance(final Position target) {
        return Math.max(calculateRankGap(target), calculateFileGap(target));
    }

    public boolean isAtSameRank(final Rank rank) {
        return this.rank.isSame(rank);
    }

    public Rank rank() {
        return rank;
    }

    public File file() {
        return file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
