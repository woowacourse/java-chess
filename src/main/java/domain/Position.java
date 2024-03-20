package domain;

import java.util.Objects;

public class Position {

    private final File file;
    private final Rank rank;

    public Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public boolean isSameFile(Position other) {
        return file == other.file;
    }

    public boolean isSameRank(Position other) {
        return rank == other.rank;
    }

    public boolean isDiagonal(Position other) {
        return fileGap(other) == rankGap(other);
    }

    private int fileGap(Position other) {
        File otherFile = other.file;
        return file.gap(otherFile);
    }

    private int rankGap(Position other) {
        Rank otherRank = other.rank;
        return rank.gap(otherRank);
    }

    public boolean hasOnlyOneRankGap(Position other) {
        return rankGap(other) == 1 && isSameFile(other);
    }

    public boolean hasOnlyOneFileGap(Position other) {
        return hasOneFileGap(other) && isSameRank(other);
    }

    private boolean hasOneFileGap(Position other) {
        return fileGap(other) == 1;
    }

    public boolean hasOnlyOneDiagonalGap(Position other) {
        return isDiagonal(other) && hasOneFileGap(other);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Position position = (Position) object;
        return Objects.equals(file, position.file) && Objects.equals(rank, position.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
