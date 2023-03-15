package chess.domain;

import java.util.Objects;

public class Position {
    private final File file;
    private final Rank rank;

    public Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public boolean isInCrossPosition(final Position otherPosition) {
        return (this.file == otherPosition.file || this.rank == otherPosition.rank) && !this.equals(otherPosition);
    }

    public boolean isInDiagonalPosition(final Position otherPosition) {
        return (calculateFileDistance(otherPosition) == calculateRankDistance(otherPosition)) && !this.equals(otherPosition);
    }

    public boolean isCrossNeighborPosition(final Position otherPosition) {
        if (isInCrossPosition(otherPosition)) {
            return calculateFileDistance(otherPosition) == 1 || calculateRankDistance(otherPosition) == 1;
        }
        return false;
    }

    public boolean isDiagonalNeighborPosition(final Position otherPosition) {
        return calculateFileDistance(otherPosition) == 1 && calculateRankDistance(otherPosition) == 1;
    }

    private int calculateFileDistance(final Position otherPosition) {
        return this.file.calculateDistance(otherPosition.file);
    }

    private int calculateRankDistance(final Position otherPosition) {
        return this.rank.calculateDistance(otherPosition.rank);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    public Rank getRank() {
        return rank;
    }

    public File getFile() {
        return file;
    }
}
