package coordinate;

import java.util.Objects;
import position.Column;
import position.Row;

public class Coordinate {

    private final Row row;
    private final Column column;

    public Coordinate(Row row, Column column) {
        this.row = row;
        this.column = column;
    }

    public int checkRow(Coordinate coordinate) {
        return row.getRowDifference(coordinate.row);
    }

    public int checkColumn(Coordinate coordinate) {
        return column.getColumnDifference(coordinate.column);
    }

    public int getRowValue() {
        return row.getPositionValue();
    }

    public void moveByDistances(int rowDistance, int columnDistance) {
        row.moveBy(rowDistance);
        column.moveBy(columnDistance);
    }

    public Coordinate copied() {
        return new Coordinate(row.copied(), column.copied());
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
