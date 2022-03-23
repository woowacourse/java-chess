package chess;

import java.util.Objects;

public class Position {

    private final Row row;
    private final Col col;

    public Position(Row row, Col col) {
        this.row = row;
        this.col = col;
    }

    public boolean isSameRow(Position other) {
        return this.row == other.row;
    }

    public boolean isSameRowOrCol(Position other) {
        return isSameRow(other) || isSameCol(other);
    }

    private boolean isSameCol(Position other) {
        return this.col == other.col;
    }

    public boolean isCross(Position other) {
        return getDistanceOfCol(other) == getDistanceOfRow(other);
    }

    public boolean isAdjacent(Position other) {
        if (isCross(other)) {
            return getDistanceOfCol(other) == 1 && getDistanceOfRow(other) == 1;
        }

        if (isSameRowOrCol(other)) {
            return getDistanceOfCol(other) == 1 || getDistanceOfRow(other) == 1;
        }

        return false;
    }

    public int getDistanceOfCol(Position other) {
        return col.getDistance(other.col);
    }

    public int getDistanceOfRow(Position other) {
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
