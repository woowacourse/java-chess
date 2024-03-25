package chess.domain.position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Position {
    private final File file;
    private final Rank rank;

    public Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public boolean isOrthogonalWith(Position other) {
        if (this.equals(other)) {
            return false;
        }
        return file == other.file || rank == other.rank;
    }

    public boolean isDiagonalWith(Position other) {
        if (this.equals(other)) {
            return false;
        }
        int fileDistance = file.calculateDistanceWith(other.file);
        int rankDistance = rank.calculateDistanceWith(other.rank);
        return fileDistance == rankDistance;
    }

    public int squaredDistanceWith(Position other) {
        int fileDistance = file.calculateDistanceWith(other.file);
        int rankDistance = rank.calculateDistanceWith(other.rank);
        return (int) Math.pow(fileDistance, 2) + (int) Math.pow(rankDistance, 2);
    }

    public boolean isRankSameWith(Rank rank) {
        return this.rank == rank;
    }

    public boolean isFurtherLeftThan(Position other) {
        return file.isFurtherLeftThan(other.file);
    }

    public boolean isFurtherRightThan(Position other) {
        return file.isFurtherRightThan(other.file);
    }

    public boolean isAbove(Position other) {
        return rank.isAbove(other.rank);
    }

    public boolean isBelow(Position other) {
        return rank.isBelow(other.rank);
    }

    public boolean isLeftLowerThan(Position other) {
        return this.isBelow(other) && this.isFurtherLeftThan(other);
    }

    public boolean isLeftUpperThan(Position other) {
        return this.isAbove(other) && this.isFurtherLeftThan(other);
    }

    public boolean isRightUpperThan(Position other) {
        return this.isAbove(other) && this.isFurtherRightThan(other);
    }

    public boolean isRightLowerThan(Position other) {
        return this.isBelow(other) && this.isFurtherRightThan(other);
    }

    public int calculateDistance(Position other) {
        int fileDistance = Math.abs(file.calculateDistanceWith(other.file));
        int rankDistance = Math.abs(rank.calculateDistanceWith(other.rank));
        return Math.max(fileDistance, rankDistance);
    }

    public List<Position> calculateSlidingPath(Position destination) {
        Direction direction = Direction.of(this, destination);
        Position start = this;
        List<Position> path = new ArrayList<>();
        while (!start.equals(destination)) {
            start = start.moveOneSpace(direction);
            path.add(start);
        }
        path.remove(path.size() - 1);
        return path;
    }

    private Position moveOneSpace(Direction direction) {
        File movedFile = file.move(direction.getMoveOnceFileWeight());
        Rank movedRank = rank.move(direction.getMoveOnceRankWeight());
        return new Position(movedFile, movedRank);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position that = (Position) o;
        return file == that.file && rank == that.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
