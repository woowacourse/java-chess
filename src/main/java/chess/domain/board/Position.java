package chess.domain.board;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class Position {

    private static final int VALUE_INDEX_ADJUST = 1;
    private static final int NEAR_DISTANCE_EXCLUDE = 2;

    private final File file;
    private final Rank rank;

    public Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    private Position getRightDownDiagonal() {
        return new Position(file.plus(), rank.minus());
    }

    private Position getDownStraight() {
        return new Position(file, rank.minus());
    }

    private Position getLeftDownDiagonal() {
        return new Position(file.minus(), rank.minus());
    }

    private Position getLeftStraight() {
        return new Position(file.minus(), rank);
    }

    public boolean isFileEquals(final Position target) {
        return file.equals(target.file);
    }

    public boolean isRankEquals(final Position target) {
        return rank.equals(target.rank);
    }

    public boolean isFileOver(final Position target) {
        return file.isOver(target.file);
    }

    public boolean isRankOver(final Position target) {
        return rank.isOver(target.rank);
    }

    public Set<Position> generateInclinationOnePath(final Position target) {
        Set<Position> positions = new HashSet<>();
        var max = Position.maxRank(this, target);
        var min = Position.minRank(this, target);

        while (max.isRankOver(min)) {
            positions.add(max);
            max = max.getLeftDownDiagonal();
        }
        positions.add(target);
        positions.remove(this);
        return positions;
    }

    public Set<Position> generateInclinationNegativeOnePath(final Position target) {
        Set<Position> positions = new HashSet<>();
        var max = Position.maxRank(this, target);
        var min = Position.minRank(this, target);

        while (max.isRankOver(min)) {
            max = max.getRightDownDiagonal();
            positions.add(max);
        }
        positions.add(target);
        positions.remove(this);
        return positions;
    }

    private static Position maxRank(final Position source, final Position target) {
        if (source.isRankOver(target)) {
            return source;
        }
        return target;
    }

    private static Position minRank(final Position source, final Position target) {
        if (source.isRankOver(target)) {
            return target;
        }
        return source;
    }

    private static Position maxFile(final Position source, final Position target) {
        if (source.isFileOver(target)) {
            return source;
        }
        return target;
    }

    private static Position minFile(final Position source, final Position target) {
        if (source.isFileOver(target)) {
            return target;
        }
        return source;
    }

    public double computeInclination(final Position target) {
        final var fileSub = target.file.sub(this.file);
        final var rankSub = target.rank.sub(this.rank);

        return fileSub / (double) rankSub;
    }

    public boolean isNear(final Position target) {
        return fileSubLessThan(target, NEAR_DISTANCE_EXCLUDE) && rankSubLessThan(target, NEAR_DISTANCE_EXCLUDE);
    }

    public boolean fileSubLessThan(final Position target, final int distance) {
        return Math.abs(this.file.sub(target.file)) < distance;
    }

    public boolean rankSubLessThan(final Position target, final int distance) {
        return Math.abs(this.rank.sub(target.rank)) < distance;
    }

    public boolean distanceEquals(final Position target, final int distance) {
        int fileDistance = this.rank.sub(target.rank);
        int rankDistance = this.file.sub(target.file);

        return fileDistance * fileDistance + rankDistance * rankDistance == distance;
    }

    public boolean rankNotEquals(final int rankExpected) {
        return this.rank.NotEquals(rankExpected);
    }

    public boolean rankSubEquals(final Position target, final int expected) {
        return this.rank.sub(target.rank) == expected;
    }

    public Set<Position> generateFilePath(final Position target) {
        Set<Position> path = new HashSet<>();
        var max = Position.maxRank(this, target);
        var min = Position.minRank(this, target);

        while (max.isRankOver(min)) {
            path.add(max);
            max = max.getDownStraight();
        }
        path.add(target);
        path.remove(this);
        return path;
    }

    public Set<Position> generateRankPath(final Position target) {
        Set<Position> path = new HashSet<>();
        var max = Position.maxFile(this, target);
        var min = Position.minFile(this, target);

        while (max.isFileOver(min)) {
            path.add(max);
            max = max.getLeftStraight();
        }
        path.add(target);
        path.remove(this);
        return path;
    }

    public int getRank() {
        return rank.getValue() - VALUE_INDEX_ADJUST;
    }

    public int getFile() {
        return file.getValue() - VALUE_INDEX_ADJUST;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    @Override
    public String toString() {
        return file.toString() + rank.toString();
    }
}
