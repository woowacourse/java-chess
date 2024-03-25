package chess.domain.square;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Square {

    private final File file;
    private final Rank rank;

    public Square(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static List<Square> allPositions() {
        return Arrays.stream(Rank.values())
                .flatMap(rank -> Arrays.stream(File.values())
                        .map(file -> new Square(file, rank)))
                .toList();
    }

    public Square createWithSameFile(Rank rank) {
        return new Square(this.file, rank);
    }

    public Square createWithSameRank(File file) {
        return new Square(file, this.rank);
    }

    public boolean isSameFile(Square other) {
        return file == other.file;
    }

    public boolean isSameRank(Square other) {
        return rank == other.rank;
    }

    public boolean isDiagonal(Square other) {
        return fileGap(other) == rankGap(other);
    }

    private int fileGap(Square other) {
        return file.gap(other.file);
    }

    private int rankGap(Square other) {
        return rank.gap(other.rank);
    }

    public boolean hasOneFileGap(Square other) {
        return fileGap(other) == 1;
    }

    public boolean hasOneRankGap(Square other) {
        return rankGap(other) == 1;
    }

    public boolean hasTwoFileGap(Square other) {
        return fileGap(other) == 2;
    }

    public boolean hasTwoRankGap(Square other) {
        return rankGap(other) == 2;
    }

    public boolean hasOneDiagonalGap(Square other) {
        return this.isDiagonal(other) && hasOneFileGap(other);
    }

    public boolean hasOnlyTwoRankGap(Square other) {
        return hasTwoRankGap(other) && isSameFile(other);
    }

    public boolean hasHigherRankByOne(Square other) {
        return rank.difference(other.rank) == 1;
    }

    public boolean hasHigherRankByTwo(Square other) {
        return rank.difference(other.rank) == 2;
    }

    public boolean isRankIncreased(Square target) {
        return target.rank.isBigger(rank);
    }

    public boolean isRankDecreased(Square target) {
        return target.rank.isLess(rank);
    }

    public List<File> findBetweenFiles(Square target) {
        return file.findBetween(target.file);
    }

    public List<Rank> findBetweenRanks(Square target) {
        return rank.findBetween(target.rank);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Square square = (Square) object;
        return Objects.equals(file, square.file) && Objects.equals(rank, square.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    @Override
    public String toString() {
        return "Position{" +
                "file=" + file +
                ", rank=" + rank +
                '}';
    }
}
