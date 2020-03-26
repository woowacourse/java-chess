package domain.point;

import java.util.Objects;

public class Point {
	private Row row;
	private Column column;

	public Point(Row row, Column column) {
		this.row = row;
		this.column = column;
	}

	public static Point of(String before) {
		String column = before.substring(0, 1);
		String row = before.substring(1, 2);

		return new Point(Row.find(row), Column.find(column));
	}

	public Point add(int rowIndex, int columnIndex) {
		return new Point(row.add(rowIndex), column.add(columnIndex));
	}

	public boolean isRowAndColumnEqual() {
		return row.getIndex() == column.getIndex();
	}

	public boolean isRowIndexZero() {
		return row.getIndex() == 0;
	}

	public boolean isColumnIndexZero() {
		return row.getIndex() == 0;
	}

	public int getRowIndex() {
		return row.getIndex();
	}

	public int getColumnIndex() {
		return column.getIndex();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
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
		return "Point{" +
				"row=" + row +
				", column=" + column +
				'}';
	}
}
