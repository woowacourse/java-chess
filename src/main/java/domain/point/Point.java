package domain.point;

import java.util.Objects;

public class Point {
	private Row row;
	private Column column;

	public Point(Row row, Column column) {
		this.row = row;
		this.column = column;
	}

	public static Point of(String location) {
		String column = location.substring(0, 1);
		String row = location.substring(1, 2);

		return new Point(Row.find(row), Column.find(column));
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
		return row.toString() + column.toString();
	}
}
