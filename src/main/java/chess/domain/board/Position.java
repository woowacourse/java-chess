package chess.domain.board;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class Position {

    private static final String CAN_NOT_COMPUTE_DIAGONAL_PATH_EXCEPTION_MESSAGE = "대각 경로를 구할 수 없는 위치입니다.";
    private static final String CAN_NOT_COMPUTE_CROSS_EXCEPTION_MESSAGE = "십자 경로를 계산할 수 없는 위치입니다.";
    private static final String CAN_NOT_COMPUTE_CROSS_DIAGONAL_EXCEPTION_MESSAGE = "십자 또는 대각 경로를 구할 수 없는 위치입니다.";

    private final File file;
    private final Rank rank;

    public Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public double computeInclination(final Position target) {
        final var fileSub = fileSub(target);
        final var rankSub = rankSub(target);

        return fileSub / (double) rankSub;
    }

    public Set<Position> computeDiagonalPath(Position target) {
        var max = maxRank(this, target);
        var min = minRank(this, target);

        double inclination = computeInclination(target);
        if (Math.abs(inclination) != 1) {
            throw new IllegalArgumentException(CAN_NOT_COMPUTE_DIAGONAL_PATH_EXCEPTION_MESSAGE);
        }

        Set<Position> path = computePathByInclination(max, min, inclination);

        path.add(target);
        path.remove(this);
        return path;
    }

    private Set<Position> computePathByInclination(Position max, Position min, final double inclination) {
        Set<Position> path = new HashSet<>();
        while (max.rank.isOver(min.rank)) {
            path.add(max);
            max = nextMax(max, inclination);
        }
        return path;
    }

    private Position nextMax(final Position max, final double inclination) {
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
            path = sameFilePath(target);
        }
        if (rank == target.rank) {
            path = sameRankPath(target);
        }
        path.add(target);
        path.remove(this);
        return path;
    }

    private Set<Position> sameFilePath(Position target) {
        var max = maxRank(this, target);
        var min = minRank(this, target);

        Set<Position> path = new HashSet<>();
        while (max.rank.isOver(min.rank)) {
            path.add(max);
            max = max.getDownStraight();
        }
        return path;
    }

    private Set<Position> sameRankPath(Position target) {
        var max = maxFile(this, target);
        var min = minFile(this, target);

        Set<Position> path = new HashSet<>();
        while (max.file.isOver(min.file)) {
            path.add(max);
            max = max.getLeftStraight();
        }
        return path;
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

    private Position maxRank(final Position source, final Position target) {
        if (source.rank.isOver(target.rank)) {
            return source;
        }
        return target;
    }

    private Position minRank(final Position source, final Position target) {
        if (source.rank.isOver(target.rank)) {
            return target;
        }
        return source;
    }

    private Position maxFile(final Position source, final Position target) {
        if (source.file.isOver(target.file)) {
            return source;
        }
        return target;
    }

    private Position minFile(final Position source, final Position target) {
        if (source.file.isOver(target.file)) {
            return target;
        }
        return source;
    }

    public boolean isRank(Rank rank) {
        return this.rank == rank;
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

    public boolean isSameFile(final Position target) {
        return file == target.file;
    }

    public int fileSub(final Position target) {
        return file.sub(target.file);
    }

    public int rankSub(final Position target) {
        return rank.sub(target.rank);
    }

    public int getRank() {
        return rank.getValue() - 1;
    }

    public int getFile() {
        return file.getValue() - 1;
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
}
