package domain.point;

import java.util.Objects;

public class Point {

    private static final int INDEX_ZERO = 0;
    private static final int INDEX_ONE = 1;
    private static final int INDEX_TWO = 2;

    private Row row;
    private Column column;

    private Point(Row row, Column column) {
        this.row = row;
        this.column = column;
    }

    public static Point of(Row row, Column column) {
        return new Point(row, column);
    }

    public static Point of(String location) {
        String column = location.substring(INDEX_ZERO, INDEX_ONE);
        String row = location.substring(INDEX_ONE, INDEX_TWO);

        return new Point(Row.findRowType(row), Column.findColumnType(column));
    }

    public int getRowDistance(Point point) {
        return row.distance(point.row);
    }

    public int getColumnDistance(Point point) {
        return column.distance(point.column);
    }

    public int getRowIndex() {
        return row.getIndex();
    }

    public int getColumnIndex() {
        return column.getIndex();
    }

    public boolean isSameColumn(Column column) {
        return this.column.equals(column);
    }

    @Override
    public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
        Point point = (Point) o;
        return row == point.row &&
            column == point.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public String toString() {
        return column.toString() + row.toString();
    }
}
