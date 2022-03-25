package chess.domain.position;

import java.util.Objects;

public class Position implements Comparable<Position> {
    private final Rank rank;
    private final File file;

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(File file, Rank rank) {
        return new Position(file, rank);
    }

    public int calculateDistance(Position position) {
        File otherFile = position.getFile();
        Rank otherRank = position.getRank();

        int rankDifference = rank.calculateAbsoluteValue(otherRank);
        int fileDifference = file.calculateAbsoluteValue(otherFile);

        if (isDiagonal(position)) {
            return rankDifference;
        }

        return rankDifference + fileDifference;
    }

    public Position toDirection(Direction direction) {
        File newFile = File.toFile((char) (direction.getFile() + file.getFile()));
        Rank newRank = Rank.toRank(direction.getRank() + rank.getRank());

        return Position.of(newFile, newRank);
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
        if (rank.calculateRank(position.getRank()) > 0) {
            return true;
        }

        return false;
    }

    public boolean isIncreaseRank(Position position) {
        if (rank.calculateRank(position.getRank()) < 0) {
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

    public Rank getRank() {
        return rank;
    }

    public File getFile() {
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

    @Override
    public int compareTo(Position other) {
        if (rank == other.getRank()) {
            return file.calculateFile(other.getFile());
        }

        return other.getRank().calculateRank(rank);
    }
}
