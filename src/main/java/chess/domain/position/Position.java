package chess.domain.position;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Position {

    private final File file;
    private final Rank rank;

    public Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static List<Position> allPositions() {
        return Arrays.stream(Rank.values())
                .flatMap(rank -> Arrays.stream(File.values())
                        .map(file -> new Position(file, rank)))
                .toList();
    }

    public Position createWithSameFile(Rank rank) {
        return new Position(this.file, rank);
    }

    public Position createWithSameRank(File file) {
        return new Position(file, this.rank);
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
        return file.gap(other.file);
    }

    private int rankGap(Position other) {
        return rank.gap(other.rank);
    }

    public boolean hasOneFileGap(Position other) {
        return fileGap(other) == 1;
    }

    public boolean hasOneRankGap(Position other) {
        return rankGap(other) == 1;
    }

    public boolean hasTwoFileGap(Position other) {
        return fileGap(other) == 2;
    }

    public boolean hasTwoRankGap(Position other) {
        return rankGap(other) == 2;
    }

    public boolean hasHigherRankByOne(Position other) {
        return rank.difference(other.rank) == 1;
    }

    public boolean hasHigherRankByTwo(Position other) {
        return rank.difference(other.rank) == 2;
    }

    public List<File> findBetweenFiles(Position target) {
        return file.findBetween(target.file);
    }

    public List<Rank> findBetweenRanks(Position target) {
        return rank.findBetween(target.rank);
    }

    @Override
    public String toString() {
        return "Position{" +
                "file=" + file +
                ", rank=" + rank +
                '}';
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
