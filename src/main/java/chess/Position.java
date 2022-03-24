package chess;

import java.util.Objects;

public class Position {

    private final Rank rank;
    private final File file;

    public Position(Rank rank, File file) {
        this.rank = rank;
        this.file = file;
    }

    public Position(String rankFile) {
        this.rank = Rank.of(rankFile.substring(1, 2));
        this.file = File.of(rankFile.substring(0, 1));
    }

    public int rankDisplacement(Position other) {
        return rank.displacement(other.rank);
    }

    public int fileDisplacement(Position other) {
        return file.displacement(other.file);
    }

    public boolean isSameFile(Position other) {
        return this.file == other.file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Position position = (Position)o;
        return rank == position.rank && file == position.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }

    public boolean isRankOf(Rank otherRank) {
        return rank == otherRank;
    }

    public boolean isSameRank(Position other) {
        return other.isRankOf(rank);
    }

    public int rankDistance(Position other) {
        return Math.abs(rank.displacement(other.rank));
    }

    public int fileDistance(Position other) {
        return Math.abs(file.displacement(other.file));
    }

    public boolean isDiagonal(Position target) {
        return this.fileDistance(target) == this.rankDistance(target);
    }

    public boolean isCross(Position other) {
        return this.isSameRank(other) || this.isSameFile(other);
    }
}
