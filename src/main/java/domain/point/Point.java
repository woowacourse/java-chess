package domain.point;

import java.util.Objects;

public class Point {
	private static final int FIRST_INDEX = 0;
	private static final int SECOND_INDEX = 1;
	private static final int END_INDEX = 2;

	private Row row;
	private Column column;

	public Point(Row row, Column column) {
		this.row = row;
		this.column = column;
	}

	public static Point of(String before) {
		String column = before.substring(FIRST_INDEX, SECOND_INDEX);
		String row = before.substring(SECOND_INDEX, END_INDEX);

		return new Point(Row.find(row), Column.find(column));
	}

	public Point add(int rowIndex, int columnIndex) {
		return new Point(row.add(rowIndex), column.add(columnIndex));
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
