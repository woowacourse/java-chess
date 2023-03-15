package techcourse.fp.chess.domain;

import java.util.Objects;

public final class Position {

    private final File file;
    private final Rank rank;

    private Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(final File file, final Rank rank) {
        return new Position(file, rank);
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }

    public int getFileOrder() {
        return file.getOrder();
    }

    public int getRankOrder() {
        return rank.getOrder();
    }

    public boolean isOnDiagonal(final Position target) {
        return Math.abs(getFileOrder() - target.getFileOrder()) == 1
                && Math.abs(getRankOrder() - target.getRankOrder()) == 1;
    }

    public boolean isUpDown(final Position target) {
        return getFileOrder() == target.getFileOrder() &&
                Math.abs(getRankOrder() - target.getRankOrder()) == 1;
    }

    public boolean isUpDownTwo(final Position target) {
        return getFileOrder() == target.getFileOrder() &&
                Math.abs(getRankOrder() - target.getRankOrder()) == 2;
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
