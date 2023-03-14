package chess.domain.board;

import java.util.Objects;

public final class Position {

    private final File file;
    private final Rank rank;

    public Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public boolean isBlackPawnInitRank() {
        return rank == Rank.SEVEN;
    }

    public boolean isWhitePawnInitRank() {
        return rank == Rank.TWO;
    }

    public Position getLeftUpDiagonal() {
        return new Position(file.minus(), rank.plus());
    }

    public Position getUpStraight() {
        return new Position(file, rank.plus());
    }

    public Position getRightUpDiagonal() {
        return new Position(file.plus(), rank.plus());
    }

    public Position getRightStraight() {
        return new Position(file.plus(), rank);
    }

    public Position getRightDownDiagonal() {
        return new Position(file.plus(), rank.minus());
    }

    public Position getDownStraight() {
        return new Position(file, rank.minus());
    }

    public Position getLeftDownDiagonal() {
        return new Position(file.minus(), rank.minus());
    }

    public Position getLeftStraight() {
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
}
