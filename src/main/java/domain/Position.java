package domain;

import java.util.Objects;

public class Position {

    private final File file;
    private final Rank rank;

    public Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
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

    public boolean isSameRank(Position other) {
        return this.rank == other.rank;
    }

    public boolean isSameFile(Position other) {
        return this.file == other.file;
    }

    public boolean isSameDiagonal(Position other) {
        return fileGap(other) == rankGap(other);
    }

    private int rankGap(Position other) {
        Rank otherRank = other.rank;
        return rank.gap(otherRank);
    }

    private int fileGap(Position other) {
        File otherFile = other.file;
        return file.gap(otherFile);
    }
}
