package domain.position;

import java.util.Objects;

public class Position {
    private final File file;
    private final Rank rank;

    public Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public Position(final String file, final int rank) {
        this(File.fromName(file), Rank.fromNumber(rank));
    }

    public Position(final String fileAndRank) {
        this(fileAndRank.substring(0, 1), Integer.parseInt(fileAndRank.substring(1)));
    }

    public boolean isStraightAt(final Position target) {
        validateSamePosition(target);
        return calculateFileGap(target) == 0 || calculateRankGap(target) == 0;
    }

    public boolean isDiagonalAt(final Position target) {
        validateSamePosition(target);
        return calculateFileGap(target) == calculateRankGap(target);
    }

    public boolean isKnightPositionAt(final Position target) {
        validateSamePosition(target);
        return calculateRankGap(target) * calculateFileGap(target) == 2;
    }

    public boolean isAdjacentAt(final Position target) {
        validateSamePosition(target);
        return Math.max(calculateRankGap(target), calculateFileGap(target)) == 1;
    }

    public boolean isDistanceAt(final Position target, final int distance) {
        return calculateRankGap(target) + calculateFileGap(target) == distance;
    }

    private void validateSamePosition(final Position target) {
        if (this.equals(target)) {
            throw new IllegalArgumentException("동일한 위치입니다.");
        }
    }

    public boolean isUpperRankThan(final Position target) {
        return this.rank.isUpperThan(target.rank);
    }

    public boolean isLowerRankThan(final Position target) {
        return this.rank.isLowerThan(target.rank);
    }

    public boolean isAtSameRank(final Rank rank) {
        return this.rank.isSame(rank);
    }

    boolean isVertical(final Position target) {
        validateSamePosition(target);
        return calculateFileGap(target) == 0 && calculateRankGap(target) > 0;
    }

    boolean isHorizontal(final Position target) {
        validateSamePosition(target);
        return calculateFileGap(target) > 0 && calculateRankGap(target) == 0;
    }

    int calculateRankGap(final Position target) {
        return Math.abs(rank.subtract(target.rank));
    }

    int calculateFileGap(final Position target) {
        return Math.abs(file.subtract(target.file));
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
