package domain.position;

import domain.movement.Direction;
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

    public List<Position> route(Position target) {
        Direction direction = this.getDirection(target);
        List<Position> positions = new ArrayList<>();
        Position current = this.next(direction);
        while (!current.equals(target)) {
            positions.add(current);
            current = current.next(direction);
        }
        return positions;
    }

    private Direction getDirection(Position target) {
        validateSamePosition(target);
        int rankDiff = target.rank.subtract(this.rank);
        int fileDiff = target.file.subtract(this.file);
        return Direction.of(rankDiff, fileDiff);
    }

    private void validateSamePosition(Position target) {
        if (this.equals(target)) {
            throw new IllegalArgumentException("동일한 위치입니다.");
        }
    }

    public Position next(Direction direction) {
        File nextFile = file.next(direction.fileDiff());
        Rank nextRank = rank.next(direction.rankDiff());
        return new Position(nextFile, nextRank);
    }

    public int calculateRankGap(Position target) {
        return Math.abs(rank.subtract(target.rank));
    }

    public int calculateFileGap(Position target) {
        return Math.abs(file.subtract(target.file));
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
