package chess.domain.piece;

import java.util.Objects;

public class Position {

    private final File file;
    private final Rank rank;

    Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public Position(final char file, final int rank) {
        this(File.from(file), Rank.from(rank));
    }

    public boolean isStraightWith(final Position target) {
        if (this.file == target.file && this.rank != target.rank) {
            return true;
        }

        if (this.file != target.file && this.rank == target.rank) {
            return true;
        }

        return false;
    }

    public boolean isDiagonalWith(final Position target) {
        return this.file.differenceWith(target.file) == this.rank.differenceWith(target.rank);
    }

    public boolean isVerticalDifference(final Position target, final int difference) {
        return this.rank.differenceWith(target.rank) == difference;
    }

    public boolean isHorizontalDifference(final Position target, final int difference) {
        return this.file.differenceWith(target.file) == difference;
    }

    public boolean isDiagonalDifference(final Position target, final int difference) {
       return isDiagonalWith(target) && this.file.differenceWith(target.file) == difference;
    }

    public boolean isSecondRank() {
        return this.rank.equals(Rank.TWO);
    }

    public boolean isForwardDifference(final Position target, final int difference) {
        return target.rank.minus(this.rank) == difference;
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
        return rank == position.rank && file == position.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }
}
