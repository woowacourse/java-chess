package coordinate;

import column.Column;
import java.util.Objects;
import row.Row;

public class Coordinate {

    private final Row row;
    private final Column column;

    public Coordinate(Row row, Column column) {
        this.row = row;
        this.column = column;
    }

    public void move(int rowPosition, int columnPosition) {
        row.move(rowPosition);
        column.move(columnPosition);
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
