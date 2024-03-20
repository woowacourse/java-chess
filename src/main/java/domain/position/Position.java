package domain.position;

import java.util.Objects;

public class Position {

    private final File file;
    private final Rank rank;

    public Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public boolean isDiagonal(Position target) {
        int fileDistance = this.file.distance(target.file);
        int rankDistance = this.rank.distance(target.rank);
        return fileDistance == rankDistance;
    }

    public boolean isStraight(Position target) {
        return this.file.isSame(target.file) || this.rank.isSame(target.rank);
    }

    public boolean isStraightDiagonal(Position target) {
        int fileDistance = this.file.distance(target.file);
        int rankDistance = this.rank.distance(target.rank);
        return (fileDistance == 1 && rankDistance == 2) || (fileDistance == 2 && rankDistance == 1);
    }

    public boolean isNeighbor(Position target) {
        int fileDistance = this.file.distance(target.file);
        int rankDistance = this.rank.distance(target.rank);
        return isDiagonalNeighbor(fileDistance, rankDistance) || isStraightNeighbor(fileDistance, rankDistance);
    }

    private boolean isDiagonalNeighbor(int fileDistance, int rankDistance) {
        return fileDistance == 1 && rankDistance == 1;
    }

    private boolean isStraightNeighbor(int fileDistance, int rankDistance) {
        return (fileDistance == 0 && rankDistance == 1) || (fileDistance == 1 && rankDistance == 0);
    }

    public boolean isForwardStraight(Position target) {
        int forwardDistance = this.rank.forwardDistance(target.rank);
        return (forwardDistance == 1 || forwardDistance == 2) && this.file.isSame(target.file);
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
