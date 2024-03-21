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
        this(File.fromSymbol(file), Rank.from(rank));
    }

    public Position(final String input) {
        this(File.fromSymbol(input.charAt(0)), Rank.from(input.charAt(1) - '0'));
    }

    public boolean isStraightWith(final Position target) {
        return isVerticalWith(target) || isHorizontalWith(target);
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

    public boolean isVerticalWithDistance(final Position target, final int distance) {
        return this.rank.getDistance(target.rank) == distance;
    }

    public boolean isHorizontalWithDistance(final Position target, final int distance) {
        return this.file.getDistance(target.file) == distance;
    }

    public boolean isDiagonalWithDistance(final Position target, final int distance) {
        return isDiagonalWith(target) && this.file.getDistance(target.file) == distance;
    }

    public boolean isForwardWithDistance(final Position target, final int distance) {
        return target.rank.minus(this.rank) == distance;
    }

    public boolean isRightDiagonalWith(final Position target) {
        return this.file.getDistance(target.file) == this.rank.getDistance(target.rank)
                && ((target.file.isBigger(this.file) && target.rank.isBigger(this.rank))
                || (this.file.isBigger(target.file) && this.rank.isBigger(target.rank)));
    }

    public boolean isLeftDiagonalWith(final Position target) {
        return this.file.getDistance(target.file) == this.rank.getDistance(target.rank)
                && ((this.file.isBigger(target.file) && target.rank.isBigger(this.rank))
                || (target.file.isBigger(this.file) && this.rank.isBigger(target.rank)));
    }

    public Set<Position> getVerticalMiddlePositions(final Position target) {
        Position start = this;
        Position end = target;

        if (this.rank.getIndex() > target.rank.getIndex()) {
            start = target;
            end = this;
        }

        final Set<Position> positions = new HashSet<>();
        for (int rankIndex = start.rank.getIndex() + 1; rankIndex < end.rank.getIndex(); rankIndex++) {
            positions.add(new Position(start.file, Rank.from(rankIndex)));
        }
        return positions;
    }

    public Set<Position> getHorizontalMiddlePositions(final Position target) {
        Position start = this;
        Position end = target;

        if (this.file.getIndex() > target.file.getIndex()) {
            start = target;
            end = this;
        }

        final Set<Position> positions = new HashSet<>();
        for (int fileIndex = start.file.getIndex() + 1; fileIndex < end.file.getIndex(); fileIndex++) {
            positions.add(new Position(File.fromIndex(fileIndex), start.rank));
        }
        return positions;
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

        while(currentRankIndex < targetRankIndex) {
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

        while(currentRankIndex < targetRankIndex) {
            positions.add(new Position(File.fromIndex(currentFileIndex), Rank.from(currentRankIndex)));
            currentFileIndex--;
            currentRankIndex++;
        }
        return positions;
    }

    public boolean isSevenRank() {
        return this.rank.equals(Rank.SEVEN);
    }

    public boolean isTwoRank() {
        return this.rank.equals(Rank.TWO);
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
}
