package domain.coordinate;

import domain.direction.Direction;
import java.util.Objects;

public class Coordinate {

    private final Position row;
    private final Position column;

    public Coordinate(Position row, Position column) {
        this.row = row;
        this.column = column;
    }

    public Coordinate(int row, int column) {
        this(new Position(row), new Position(column));
    }

    public int calculateRowDifference(Coordinate coordinate) {
        return row.calculateDifference(coordinate.row);
    }

    public int calculateColumnDifference(Coordinate coordinate) {
        return column.calculateDifference(coordinate.column);
    }

    public boolean hasSameRowPosition(Position row) {
        return this.row.equals(row);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coordinate that = (Coordinate) o;
        return Objects.equals(row, that.row) && Objects.equals(column, that.column);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
