package domain.coordinate;

import java.util.Arrays;
import java.util.Objects;

public class Coordinate {
	private static final int FIRST_INDEX = 0;
	private static final int SECOND_INDEX = 1;
	private static final int END_INDEX = 2;

	private final Row row;
	private final Column column;

	public Coordinate(Row row, Column column) {
		this.row = row;
		this.column = column;
	}

	public static Coordinate of(String before) {
		String column = before.substring(FIRST_INDEX, SECOND_INDEX);
		String row = before.substring(SECOND_INDEX, END_INDEX);

		return new Coordinate(Row.find(row), Column.find(column));
	}

	public Coordinate add(int rowIndex, int columnIndex) {
		return new Coordinate(row.add(rowIndex), column.add(columnIndex));
	}

	public boolean matchColumn(Column column) {
		return this.column.equals(column);
	}

	public boolean matchRow(final Row... rows) {
		return Arrays.asList(rows).contains(row);
	}

	public int getRowIndex() {
		return row.getIndex();
	}

	public int getColumnIndex() {
		return column.getIndex();
	}

	public String getRepresentation() {
		return column.getRepresentation() + row.getRepresentation();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Coordinate coordinate = (Coordinate) o;
		return row == coordinate.row &&
				column == coordinate.column;
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
