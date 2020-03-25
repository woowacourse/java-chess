package domain.point;

import java.util.Objects;

public class Point {
	private Row row;
	private Column column;

	public Point(Row row, Column column) {
		this.row = row;
		this.column = column;
	}

	public Row getRow() {
		return row;
	}

	public Column getColumn() {
		return column;
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
