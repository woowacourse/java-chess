package chess.domain.board;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class Position {

    private static final String CAN_NOT_COMPUTE_DIAGONAL_PATH_EXCEPTION_MESSAGE = "대각 경로를 구할 수 없는 위치입니다.";
    private static final String CAN_NOT_COMPUTE_CROSS_EXCEPTION_MESSAGE = "십자 경로를 계산할 수 없는 위치입니다.";
    private static final String CAN_NOT_COMPUTE_CROSS_DIAGONAL_EXCEPTION_MESSAGE = "십자 또는 대각 경로를 구할 수 없는 위치입니다.";
    private static final int INDEX_ADJUSTMENT = 1;

    private final File file;
    private final Rank rank;

    public Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public Set<Position> computeCrossOrDiagonalPath(Position target) {
        if (Math.abs(computeInclination(target)) == 1) {
            return computeDiagonalPath(target);
        }
        if (file == target.file || rank == target.rank) {
            return computeCrossPath(target);
        }

        throw new IllegalArgumentException(CAN_NOT_COMPUTE_CROSS_DIAGONAL_EXCEPTION_MESSAGE);
    }

    public Set<Position> computeDiagonalPath(Position target) {
        double inclination = computeInclination(target);
        if (Math.abs(inclination) != 1) {
            throw new IllegalArgumentException(CAN_NOT_COMPUTE_DIAGONAL_PATH_EXCEPTION_MESSAGE);
        }

        Set<Position> path = computePathByInclination(getMaxRankPosition(this, target),
                getMinRankPosition(this, target), inclination);

        path.add(target);
        path.remove(this);
        return path;
    }

    public double computeInclination(final Position target) {
        return fileSub(target) / (double) rankSub(target);
    }

    private Set<Position> computePathByInclination(Position max, Position min, final double inclination) {
        Set<Position> path = new HashSet<>();
        while (max.rank.isOver(min.rank)) {
            path.add(max);
            max = getNextMax(max, inclination);
        }
        return path;
    }

    private Position getNextMax(final Position max, final double inclination) {
        if (inclination == 1) {
            return max.getLeftDownDiagonal();
        }
        return max.getRightDownDiagonal();
    }

    public Set<Position> computeCrossPath(Position target) {
        Set<Position> path = new HashSet<>();
        if (file != target.file && rank != target.rank) {
            throw new IllegalArgumentException(CAN_NOT_COMPUTE_CROSS_EXCEPTION_MESSAGE);
        }
        if (file == target.file) {
            path = computeSameFilePath(target);
        }
        if (rank == target.rank) {
            path = computeSameRankPath(target);
        }
        path.add(target);
        path.remove(this);
        return path;
    }

    private Set<Position> computeSameFilePath(Position target) {
        var max = getMaxRankPosition(this, target);
        var min = getMinRankPosition(this, target);

        Set<Position> path = new HashSet<>();
        while (max.rank.isOver(min.rank)) {
            path.add(max);
            max = max.getDownStraight();
        }
        return path;
    }

    private Set<Position> computeSameRankPath(Position target) {
        var max = getMaxFilePosition(this, target);
        var min = getMinFilePosition(this, target);

        Set<Position> path = new HashSet<>();
        while (max.file.isOver(min.file)) {
            path.add(max);
            max = max.getLeftStraight();
        }
        return path;
    }

    public boolean isRank(Rank rank) {
        return this.rank == rank;
    }

    public boolean isSameFile(final Position target) {
        return file == target.file;
    }

    public int fileSub(final Position target) {
        return file.sub(target.file);
    }

    public int rankSub(final Position target) {
        return rank.sub(target.rank);
    }

    private Position getMaxRankPosition(final Position source, final Position target) {
        if (source.rank.isOver(target.rank)) {
            return source;
        }
        return target;
    }

    private Position getMinRankPosition(final Position source, final Position target) {
        if (source.rank.isOver(target.rank)) {
            return target;
        }
        return source;
    }

    private Position getMaxFilePosition(final Position source, final Position target) {
        if (source.file.isOver(target.file)) {
            return source;
        }
        return target;
    }

    private Position getMinFilePosition(final Position source, final Position target) {
        if (source.file.isOver(target.file)) {
            return target;
        }
        return source;
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
        return "Position{" +
                "rank=" + file +
                ", file=" + rank +
                '}';
    }

    public int getRank() {
        return rank.getValue() - INDEX_ADJUSTMENT;
    }

    public int getFile() {
        return file.getValue() - INDEX_ADJUSTMENT;
    }
}
