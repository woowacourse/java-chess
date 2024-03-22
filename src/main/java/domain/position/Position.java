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

    public List<Position> route(Position target) { // todo direction 삭제하면서 리팩토링
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
        validateDifferentPosition(target);
        int rankDiff = target.rank.subtract(this.rank);
        int fileDiff = target.file.subtract(this.file);
        return Direction.of(rankDiff, fileDiff);
    }

    public void validateDifferentPosition(Position target) {
        if (this.equals(target)) {
            throw new IllegalArgumentException("같은 칸입니다.");
        }
    }

    public Position next(Direction direction) { // todo 삭제
        File nextFile = file.next(direction.fileDiff());
        Rank nextRank = rank.next(direction.rankDiff());
        return new Position(nextFile, nextRank);
    }

    public boolean isStraightDirectionTo(Position target) {
        validateDifferentPosition(target);
        return calculateFileGap(target) == 0 || calculateRankGap(target) == 0;
    }

    public boolean isDiagonalDirectionTo(Position target) {
        validateDifferentPosition(target);
        return calculateFileGap(target) == calculateRankGap(target);
    }

    public boolean isKnightDirectionTo(Position target) {
        validateDifferentPosition(target);
        return calculateFileGap(target) * calculateRankGap(target) == 2;
    }

    public boolean isVerticalDirectionTo(Position target) {
        validateDifferentPosition(target);
        return calculateFileGap(target) == 0;
    }

    public int calculateDistance(Position target) {
        return Math.max(calculateRankGap(target), calculateFileGap(target));
    }

    private int calculateRankGap(Position target) {
        return Math.abs(rank.subtract(target.rank));
    }

    private int calculateFileGap(Position target) {
        return Math.abs(file.subtract(target.file));
    }

    public boolean isUpperRankThan(Position target) {
        return this.rank.isUpperThan(target.rank);
    }

    public boolean isLowerRankThan(Position target) {
        return this.rank.isLowerThan(target.rank);
    }

    public boolean isSameRank(Rank rank) {
        return this.rank == rank;
    }

    public Rank rank() {
        return rank;
    }

    public File file() {
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
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
