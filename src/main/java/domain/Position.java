package domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Position {

    private static final List<Position> CACHE = Arrays.stream(File.values())
            .flatMap(file -> Arrays.stream(Rank.values())
                    .map(rank -> new Position(file, rank)))
            .toList();

    private final File file;
    private final Rank rank;

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position valueOf(File file, Rank rank) {
        return CACHE.stream()
                .filter(position -> position.file.equals(file))
                .filter(position -> position.rank.equals(rank))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("a1 ~ h8 사이로 입력해주세요."));
    }

    public Position createWithSameRank(File file) {
        return valueOf(file, this.rank);
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
        return file.gap(other.file);
    }

    private int rankGap(Position other) {
        return rank.gap(other.rank);
    }

    public boolean hasOneFileGap(Position other) {
        return fileGap(other) == 1;
    }

    public boolean hasTwoFileGap(Position other) {
        return file.confirmGap(other.file, 2);
    }

    public boolean hasOneRankGap(Position other) {
        return rankGap(other) == 1;
    }

    public boolean hasTwoRankGap(Position other) {
        return rank.confirmGap(other.rank, 2);
    }

    public boolean hasOnlyTwoRankGap(Position other) {
        return hasTwoRankGap(other) && isSameFile(other);
    }

    public boolean hasOneDiagonalGap(Position other) {
        return this.isDiagonal(other) && hasOneFileGap(other);
    }

    public boolean isRankIncreased(Position target) {
        return target.rank.isBigger(rank);
    }

    public boolean isRankDecreased(Position target) {
        return target.rank.isLess(rank);
    }

    public boolean isBlackPawnRank() {
        return InitPosition.isBlackPawnRank(rank);
    }

    public boolean isWhitePawnRank() {
        return InitPosition.isWhitePawnRank(rank);
    }

    public List<File> findBetweenFile(Position target) {
        return file.findBetween(target.file);
    }

    public List<Rank> findBetweenRank(Position target) {
        return rank.findBetween(target.rank);
    }

    public Position createWithSameFile(Rank rank) {
        return valueOf(this.file, rank);
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
