package techcourse.fp.chess.domain;

import java.util.Objects;

public final class Position {

    private static final int ONE_SPACE = 1;
    private static final int TWO_SPACES = 2;

    private final File file;
    private final Rank rank;

    private Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(final File file, final Rank rank) {
        return new Position(file, rank);
    }

    public boolean isOnDiagonal(final Position target) {
        return Math.abs(getFileOrder() - target.getFileOrder()) == ONE_SPACE
                && Math.abs(getRankOrder() - target.getRankOrder()) == ONE_SPACE;
    }

    public boolean isUpDown(final Position target) {
        return getFileOrder() == target.getFileOrder() &&
                Math.abs(getRankOrder() - target.getRankOrder()) == ONE_SPACE;
    }

    public boolean isUpDownTwo(final Position target) {
        return getFileOrder() == target.getFileOrder() &&
                Math.abs(getRankOrder() - target.getRankOrder()) == TWO_SPACES;
    }

    public int getFileOrder() {
        return file.getOrder();
    }

    public int getRankOrder() {
        return rank.getOrder();
    }

    public int getGapOfFileOrder(Position other) {
        return this.getFileOrder() - other.getFileOrder();
    }

    public int getGapOfRankOrder(Position other) {
        return this.getRankOrder() - other.getRankOrder();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Position otherPosition = (Position) other;
        return file == otherPosition.file && rank == otherPosition.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
