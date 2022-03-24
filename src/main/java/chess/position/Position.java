package chess.position;

import java.util.Objects;

/*
abcdefgh -> col
12345678 -> row
 */


public class Position {

    private final File file;
    private final Rank rank;

    public Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public boolean isVerticalWay(Position other) {
        return this.file == other.file;
    }

    public boolean isHorizontalWay(Position other) {
        return this.rank == other.rank;
    }

    public boolean isDiagonalWay(Position other) {
        return getVerticalDistance(other) == getHorizontalDistance(other);
    }

    public boolean isAdjacent(Position other) {
        if (isDiagonalWay(other)) {
            return getVerticalDistance(other) == 1 && getHorizontalDistance(other) == 1;
        }

        if (isVerticalWay(other)) {
            return getHorizontalDistance(other) == 0 && getVerticalDistance(other) == 1;
        }

        if (isHorizontalWay(other)) {
            return getHorizontalDistance(other) == 1 && getVerticalDistance(other) == 0;
        }

        return false;
    }

    public boolean isUpward(Position other) {
        return rank.isUpward(other.rank);
    }

    public boolean isDownward(Position other) {
        return rank.isDownward(other.rank);
    }

    public boolean isSameRank(Rank rank) {
        return this.rank == rank;
    }

    public int getVerticalDistance(Position other) {
        return rank.getDistance(other.rank);
    }

    public int getHorizontalDistance(Position other) {
        return file.getDistance(other.file);
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

    @Override
    public String toString() {
        return "Position{" +
            "row=" + file +
            ", col=" + rank +
            '}';
    }
}
