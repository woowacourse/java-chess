package chess.domain.piece;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Position {

    private final File file;
    private final Rank rank;

    public Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public Position(final String input) {
        this(File.fromSymbol(input.charAt(0)), Rank.from(input.charAt(1) - '0'));
    }

    public boolean isVerticalWith(final Position target) {
        return this.file == target.file && this.rank != target.rank;
    }

    public boolean isHorizontalWith(final Position target) {
        return this.file != target.file && this.rank == target.rank;
    }

    public boolean isDiagonalWith(final Position target) {
        return this.file.getDistance(target.file) == this.rank.getDistance(target.rank);
    }

    public boolean isDownWith(final Position target) {
        return target.rank.isBigger(this.rank);
    }

    public boolean isLeftWith(final Position target) {
        return target.file.isBigger(this.file);
    }

    public boolean isRightDiagonalWith(final Position target) {
        return isDiagonalWith(target)
                && ((target.file.isBigger(this.file) && target.rank.isBigger(this.rank))
                || (this.file.isBigger(target.file) && this.rank.isBigger(target.rank)));
    }

    public boolean isLeftDiagonalWith(final Position target) {
        return isDiagonalWith(target)
                && ((this.file.isBigger(target.file) && target.rank.isBigger(this.rank))
                || (target.file.isBigger(this.file) && this.rank.isBigger(target.rank)));
    }

    public Set<Position> getRightDiagonalMiddlePositions(final Position target) {
        Position start = this;
        Position end = target;

        if (this.rank.getIndex() > target.rank.getIndex()) {
            start = target;
            end = this;
        }

        final Set<Position> positions = new HashSet<>();

        int currentRankIndex = start.rank.getIndex() + 1;
        int currentFileIndex = start.file.getIndex() + 1;
        int targetRankIndex = end.rank.getIndex();

        while (currentRankIndex < targetRankIndex) {
            positions.add(new Position(File.fromIndex(currentFileIndex), Rank.from(currentRankIndex)));
            currentFileIndex++;
            currentRankIndex++;
        }
        return positions;
    }

    public Set<Position> getLeftDiagonalMiddlePositions(Position target) {
        Position start = this;
        Position end = target;

        if (this.rank.getIndex() > target.rank.getIndex()) {
            start = target;
            end = this;
        }

        final Set<Position> positions = new HashSet<>();

        int currentRankIndex = start.rank.getIndex() + 1;
        int currentFileIndex = start.file.getIndex() - 1;
        int targetRankIndex = end.rank.getIndex();

        while (currentRankIndex < targetRankIndex) {
            positions.add(new Position(File.fromIndex(currentFileIndex), Rank.from(currentRankIndex)));
            currentFileIndex--;
            currentRankIndex++;
        }
        return positions;
    }

    public int getRankDistance(final Position target) {
        return this.rank.getDistance(target.rank);
    }

    public int getFileDistance(final Position target) {
        return this.file.getDistance(target.file);
    }

    public boolean isRank(final Rank rank) {
        return this.rank.equals(rank);
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
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

    public Position up() {
        return new Position(this.file, this.rank.up());
    }

    public Position right() {
        return new Position(this.file.right(), this.rank);
    }
}
