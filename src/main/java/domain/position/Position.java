package domain.position;

import java.util.Objects;

public final class Position {

    private static final int FILE_OFFSET = 0;
    private static final int RANK_OFFSET = 1;
    private static final int FILE_STAY = 0;

    private final File file;
    private final Rank rank;

    Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public boolean isDiagonal(Position other) {
        if (this.equals(other)) {
            return false;
        }

        return getFileDifference(other) == getRankDifference(other);
    }

    private int getFileDifference(final Position other) {
        return Math.abs(this.getFile() - other.getFile());
    }

    private int getRankDifference(final Position other) {
        return Math.abs(this.getRank() - other.getRank());
    }

    public boolean isStraight(Position other) {
        if (this.equals(other) || isDiagonal(other)) {
            return false;
        }

        return other.getRank() == this.getRank() || other.getFile() == this.getFile();
    }

    public int getDistance(Position other) {
        return getFileDifference(other) + getRankDifference(other);
    }

    public Position moveUpRight(int distance) {
        return move(distance, distance);
    }

    public Position moveUpLeft(int distance) {
        return move(distance, -distance);
    }

    public Position moveDownRight(int distance) {
        return move(-distance, distance);
    }

    public Position moveDownLeft(int distance) {
        return move(-distance, -distance);
    }

    public Position moveUp(int distance) {
        return move(distance, FILE_STAY);
    }

    public Position moveDown(int distance) {
        return move(-distance, FILE_STAY);
    }

    public Position move(int rankDifference, int fileDifference) {
        final int rank = this.getRank() + rankDifference;
        final int file = this.getFile() + fileDifference;

        return Positions.from(String.valueOf((char) file) + (char) rank);
    }

    public String getName() {
        return file.getName() + rank.getName();
    }

    public int getFile() {
        return this.getName().charAt(FILE_OFFSET);
    }

    public int getRank() {
        return this.getName().charAt(RANK_OFFSET);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Position)) {
            return false;
        }
        final Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
