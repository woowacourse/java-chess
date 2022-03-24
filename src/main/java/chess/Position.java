package chess;

import java.util.Objects;

public class Position {

    private final Row row;
    private final Col col;

    public Position(Row row, Col col) {
        this.row = row;
        this.col = col;
    }

    public boolean isVerticalWay(Position other) {
        return this.row == other.row;
    }

    public boolean isHorizontalWay(Position other) {
        return this.col == other.col;
    }

    public boolean isDiagonalWay(Position other) {
        return getHorizontalDistance(other) == getVerticalDistance(other);
    }

    public boolean isAdjacent(Position other) {
        if (isDiagonalWay(other)) {
            return getHorizontalDistance(other) == 1 && getVerticalDistance(other) == 1;
        }

        if (isVerticalWay(other)) {
            return getVerticalDistance(other) == 0 && getHorizontalDistance(other) == 1;
        }

        if (isHorizontalWay(other)) {
            return getVerticalDistance(other) == 1 && getHorizontalDistance(other) == 0;
        }

        return false;
    }

    public int getHorizontalDistance(Position other) {
        return col.getDistance(other.col);
    }

    public int getVerticalDistance(Position other) {
        return row.getDistance(other.row);
    }

    public Direction getDirectionTo(Position other) {
        return col.getDirection(other.col);
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
        return row == position.row && col == position.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public String toString() {
        return "Position{" +
            "row=" + row +
            ", col=" + col +
            '}';
    }
}
