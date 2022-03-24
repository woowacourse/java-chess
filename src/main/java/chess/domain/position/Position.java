package chess.domain.position;

import java.util.Objects;

public class Position {
    private final Rank rank;
    private final File file;

    public Position(Rank rank, File file) {
        this.rank = rank;
        this.file = file;
    }

    public boolean isSameRank(Position position) {
        Rank rank = position.getRank();

        if (this.rank == rank) {
            return true;
        }

        return false;
    }

    public boolean isSameFile(Position position) {
        File file = position.getFile();

        if (this.file == file) {
            return true;
        }

        return false;
    }

    public boolean isReductionRank(Position position) {
        if (rank.calculateRank(rank) > 0) {
            return true;
        }

        return false;
    }

    public boolean isIncreaseRank(Position position) {
        if (rank.calculateRank(rank) < 0) {
            return true;
        }

        return false;
    }

    public boolean isDiagonal(Position position) {
        Rank rank = position.getRank();
        File file = position.getFile();

        int rankDifference = this.rank.calculateAbsoluteValue(rank);
        int fileDifference = this.file.calculateAbsoluteValue(file);

        if (rankDifference == fileDifference) {
            return true;
        }

        return false;
    }

    private Rank getRank() {
        return rank;
    }

    private File getFile() {
        return file;
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
        return rank == position.rank && file == position.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }
}
