package chess;

import java.util.Objects;

public class Square {
    private final File file;
    private final Rank rank;

    public Square(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public boolean isSameRank(Square targetSquare) {
        return this.rank == targetSquare.rank;
    }

    public boolean isSameFile(Square targetSquare) {
        return this.file == targetSquare.file;
    }

    public int calculateRankDistance(Square targetSquare) {
        return this.rank.calculateDistance(targetSquare.rank);
    }

    public int calculateFileDistance(Square targetSquare) {
        return this.file.calculateDistance(targetSquare.file);
    }

    public int calculateRankDifference(Square targetSquare) {
        return this.rank.calculateDifference(targetSquare.rank);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return Objects.equals(file, square.file) && Objects.equals(rank, square.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
