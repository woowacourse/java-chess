package chess.board;

import java.util.Objects;

public class Position {

    private final File file;
    private final Rank rank;

    public Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(String fileName, int rankNumber) {
        return new Position(File.from(fileName), Rank.from(rankNumber));
    }

    public boolean isOnSameRank(Position other) {
        return rank == other.rank;
    }

    public boolean isOnSameFile(Position other) {
        return file == other.file;
    }

    public boolean isOnPositiveSlopeDiagonal(Position other) {
        int rankDifference = rank.calculateDifference(other.rank);
        int fileDifference = file.calculateDifference(other.file);
        return hasSameAbsoluteDifference(rankDifference, fileDifference) &&
                hasSameSign(rankDifference, fileDifference);
    }

    public boolean isOnNegativeSlopeDiagonal(Position other) {
        int rankDifference = rank.calculateDifference(other.rank);
        int fileDifference = file.calculateDifference(other.file);
        return hasSameAbsoluteDifference(rankDifference, fileDifference) &&
                hasDifferentSign(rankDifference, fileDifference);
    }

    private boolean hasDifferentSign(int rankDifference, int fileDifference) {
        return rankDifference * fileDifference < 0;
    }

    private boolean hasSameSign(int rankDifference, int fileDifference) {
        return rankDifference * fileDifference >= 0;
    }

    private boolean hasSameAbsoluteDifference(int rankDifference, int fileDifference) {
        return Math.abs(rankDifference) == Math.abs(fileDifference);
    }

    public boolean isOnKnightRoute(Position other) {
        return Math.abs(rank.calculateDifference(other.rank) * file.calculateDifference(other.file)) == 2;
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
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
