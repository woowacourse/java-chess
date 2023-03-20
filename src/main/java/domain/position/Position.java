package domain.position;

import java.util.Objects;

public final class Position {

    private static final int FILE_OFFSET = 0;
    private static final int RANK_OFFSET = 1;

    private final File file;
    private final Rank rank;

    Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public int getFileDifference(final Position other) {
        return other.getFile() - this.getFile();
    }

    public int getRankDifference(final Position other) {
        return other.getRank() - this.getRank();
    }

    public int getDistance(final Position other) {
        return Math.abs(getFileDifference(other)) + Math.abs(getRankDifference(other));
    }

    public Position move(final Direction direction) {
        final int rank = this.getRank() + direction.getRankDifference();
        final int file = this.getFile() + direction.getFileDifference();

        return Positions.from(String.valueOf((char) file) + (char) rank);
    }

    public Position move(final Direction direction, final int distance) {
        Position result = this;
        for (int i = 0; i < distance; i++) {
            result = result.move(direction);
        }

        return result;
    }

    public String getName() {
        return file.getName() + rank.getName();
    }

    private int getFile() {
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
