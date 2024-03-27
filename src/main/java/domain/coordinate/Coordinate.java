package domain.coordinate;

import domain.coordinate.position.Column;
import domain.coordinate.position.Position;
import domain.coordinate.position.Row;
import domain.direction.Direction;
import java.util.Objects;
import view.translator.ColumnSymbol;

public class Coordinate {

    private final Column column;
    private final Row row;

    private Coordinate(Row row, Column column) {
        this.row = row;
        this.column = column;
    }

    public static Coordinate from(String coordinate) {
        String[] splitCoordinate = coordinate.split("");
        int column = ColumnSymbol.from(splitCoordinate[0]).getPosition();
        int row = Integer.parseInt(splitCoordinate[1]);

        return new Coordinate(new Row(new Position(row)), new Column(new Position(column)));
    }

    public int calculateRowDifference(Coordinate coordinate) {
        return row.getRowDifference(coordinate.row);
    }

    public int calculateColumnDifference(Coordinate coordinate) {
        return column.getColumnDifference(coordinate.column);
    }

    public boolean isSameRowPosition(int otherPosition) {
        return row.isSamePosition(otherPosition);
    }

    public Coordinate next(Direction direction) {
        return new Coordinate(row.next(direction.getRowDirection()), column.next(direction.getColumnDirection()));
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
