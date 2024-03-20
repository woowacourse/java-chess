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
        return isVerticalWith(target) || isHorizontalWith(target);
    }

    public boolean isVerticalWithDistance(final Position target, final int distance) {
        return this.rank.getDistance(target.rank) == distance;
    }

    public boolean isHorizontalWithDistance(final Position target, final int distance) {
        return this.file.getDistance(target.file) == distance;
    }

    public boolean isDiagonalWith(final Position target) {
        return this.file.getDistance(target.file) == this.rank.getDistance(target.rank);
    }

    public boolean isDiagonalWithDistance(final Position target, final int distance) {
       return isDiagonalWith(target) && this.file.getDistance(target.file) == distance;
    }

    public boolean isTwoRank() {
        return this.rank.equals(Rank.TWO);
    }

    public boolean isForwardWithDistance(final Position target, final int distance) {
        return target.rank.minus(this.rank) == distance;
    }

    private boolean isVerticalWith(final Position target) {
        return this.file != target.file && this.rank == target.rank;
    }

    private boolean isHorizontalWith(final Position target) {
        return this.file == target.file && this.rank != target.rank;
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
