package chess;

import java.util.Objects;

public class Position {

    private final Row row;
    private final Col col;

    public Position(Row row, Col col) {
        this.row = row;
        this.col = col;
    }

    public Row getRow() {
        return row;
    }

    public Col getCol() {
        return col;
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
        int colDiff = Math.abs(col.getValue() - other.col.getValue());
        int rowDiff = Math.abs(row.getValue() - other.row.getValue());

        return colDiff == rowDiff;
    }

    public int getDistance(Position other) {
        return col.getDiff(other.col);
    }

    public Direction getDirection(Position to) {
        return col.getDirection(to.col);
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
