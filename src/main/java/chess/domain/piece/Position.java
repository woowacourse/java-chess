package chess.domain.piece;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    public Set<Position> getForwardVerticalMiddlePositions(Position target) {
        final Set<Position> positions = new HashSet<>();
        for (int rankIndex = this.rank.getNumber() + 1; rankIndex < target.rank.getNumber(); rankIndex++) {
            positions.add(new Position(this.file, Rank.from(rankIndex)));
        }
        return positions;
    }

    public Set<Position> getBackVerticalMiddlePositions(Position target) {
        final Set<Position> positions = new HashSet<>();
        for (int rankIndex = this.rank.getNumber() - 1; rankIndex > target.rank.getNumber(); rankIndex--) {
            positions.add(new Position(this.file, Rank.from(rankIndex)));
        }
        return positions;
    }

    public boolean isForwardVerticalWith(Position target) {
        return target.rank.minus(this.rank) > 0;
    }

    public boolean isBackwardVerticalWith(final Position target) {
        return target.rank.minus(this.rank) < 0;
    }

    public boolean isRightHorizontalWith(final Position target) {
        return target.file.minus(this.file) > 0;
    }

    public Set<Position> getRightHorizontalMiddlePositions(final Position target) {
        final Set<Position> positions = new HashSet<>();
        for (int fileIndex = this.file.getIndex() + 1; fileIndex < target.file.getIndex(); fileIndex++) {
            positions.add(new Position(File.from(fileIndex), this.rank));
        }
        return positions;
    }

    public boolean isLeftHorizontalWith(final Position target) {
        return target.file.minus(this.file) < 0;
    }

    public Set<Position> getLeftHorizontalMiddlePositions(final Position target) {
        final Set<Position> positions = new HashSet<>();
        for (int fileIndex = this.file.getIndex() - 1; fileIndex > target.file.getIndex(); fileIndex--) {
            positions.add(new Position(File.from(fileIndex), this.rank));
        }
        return positions;
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
