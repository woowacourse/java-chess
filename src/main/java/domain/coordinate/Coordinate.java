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
        this(Position.of(row), Position.of(column));
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

    public Coordinate moveOneStep(Direction direction) {
        Position row = this.row.moveBy(direction.getRowOffset());
        Position column = this.column.moveBy(direction.getColumnOffset());

        return new Coordinate(row, column);
    }

    public int calculateDistanceToDestination(Direction direction, Coordinate destination) {
        return direction.calculateDistance(
                calculateRowDifference(destination), calculateColumnDifference(destination)
        );
    }

    public Position getRow() {
        return row;
    }

    public Position getColumn() {
        return column;
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
