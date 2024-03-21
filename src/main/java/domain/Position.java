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

//    public boolean hasOnlyOneRankGap(Position other) {
//        return hasOneRankGap(other) && isSameFile(other);
//    }

    public boolean hasOneRankGap(Position other) {
        return rankGap(other) == 1;
    }

//    public boolean hasOnlyOneFileGap(Position other) {
//        return hasOneFileGap(other) && isSameRank(other);
//    }

    public boolean hasOneFileGap(Position other) {
        return fileGap(other) == 1;
    }

//    public boolean hasOnlyOneDiagonalGap(Position other) {
//        return isDiagonal(other) && hasOneFileGap(other);
//    }

//    public boolean isRankIncreased (Position other) {
//        return rank.
//    }

//    private boolean hasTwoRankGap(Position other) {
//        return rankGap(other) == 2;
//    }

    public boolean hasOneDiagonalGap(Position other) {
        return this.isDiagonal(other) && hasOneFileGap(other);
    }


    public boolean hasTwoFileGap(Position other) {
        return file.confirmGap(other.file, 2);
    }

    public boolean hasOnlyTwoRankGap(Position other) {
        return rank.confirmGap(other.rank, 2) && isSameFile(other);
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

    public boolean isRankIncreased(Position target) {
        return target.rank.isBigger(rank);
    }

    public boolean isRankDecreased(Position target) {
        return target.rank.isLess(rank);
    }

    public boolean isInitPosition() {
        return this.rank == InitPosition.blackPawnRank();
    }

    public boolean isBlackPawnRank() {
//        return InitPosition.isBlackPawnRank(rank);
        return InitPosition.PAWN.rank(Side.BLACK) == this.rank;
    }

    public boolean isWhitePawnRank() {
        return InitPosition.isWhitePawnRank(rank);
    }
}
