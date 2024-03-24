package domain;

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
        return isSameRank(other.rank);
    }

    public boolean isSameRank(Rank otherRank) {
        return rank == otherRank;
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

    public boolean hasOneRankGap(Position other) {
        return rankGap(other) == 1;
    }

    public boolean hasOneFileGap(Position other) {
        return fileGap(other) == 1;
    }

    public boolean hasOneDiagonalGap(Position other) {
        return this.isDiagonal(other) && hasOneFileGap(other);
    }

    public boolean hasTwoFileGap(Position other) {
        return file.confirmGap(other.file, 2);
    }

    public boolean hasOnlyTwoRankGap(Position other) {
        return hasTwoRankGap(other) && isSameFile(other);
    }

    public boolean hasTwoRankGap(Position other) {
        return rank.confirmGap(other.rank, 2);
    }

    public boolean isRankIncreased(Position target) {
        return target.rank.isBigger(rank);
    }

    public boolean isRankDecreased(Position target) {
        return target.rank.isLess(rank);
    }

    public List<File> findBetweenFiles(Position target) {
        return file.findBetween(target.file);
    }

    public List<Rank> findBetweenRanks(Position target) {
        return rank.findBetween(target.rank);
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
