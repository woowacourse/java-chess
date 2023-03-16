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

    public boolean isDiagonal(Position other) {
        if (this.equals(other)) {
            return false;
        }

        final int otherFile = other.getFile();
        final int otherRank = other.getRank();
        final int thisFile = this.getFile();
        final int thisRank = this.getRank();

        return Math.abs(thisFile - otherFile) == Math.abs(thisRank - otherRank);
    }

    public boolean isStraight(Position other) {
        if (this.equals(other) || isDiagonal(other)) {
            return false;
        }

        final int otherFile = other.getFile();
        final int otherRank = other.getRank();
        final int thisFile = this.getFile();
        final int thisRank = this.getRank();

        return otherRank == thisRank || otherFile == thisFile;
    }

    public int getDistance(Position other) {
        final int otherFile = other.getFile();
        final int otherRank = other.getRank();
        final int thisFile = this.getFile();
        final int thisRank = this.getRank();

        return Math.abs(thisFile - otherFile) + Math.abs(thisRank - otherRank);
    }

    public Position moveDown(int distance) {
        return move(-distance, 0);
    }

    public Position moveUp(int distance) {
        return move(distance, 0);
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
