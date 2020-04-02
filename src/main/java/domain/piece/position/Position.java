package domain.piece.position;

import java.util.Arrays;

public class Position {
	private Column column;
	private Row row;

	Position(Column column, Row row) {
		this.column = column;
		this.row = row;
	}

	public static Position of(String position) {
		return PositionCache.of(position);
	}

	public static Position of(Column column, Row row) {
		return of(column.getColumnName() + row.getNumber());
	}

	public static Position of(int inputColumn, int inputRow) {
		Column column = Arrays.stream(Column.values())
			.filter(value -> value.getNumber() == inputColumn)
			.findFirst()
			.orElseThrow(() -> new InvalidPositionException(InvalidPositionException.INVALID_POSITON));

		Row row = Arrays.stream(Row.values())
			.filter(value -> value.getNumber() == inputRow)
			.findFirst()
			.orElseThrow(() -> new InvalidPositionException(InvalidPositionException.INVALID_POSITON));

		return of(column, row);
	}

	public int calculateColumnGap(Position targetPosition) {
		return column.gap(targetPosition.column);
	}

	public int calculateRowGap(Position targetPosition) {
		return row.gap(targetPosition.row);
	}

	public boolean isSamePosition(Position position) {
		return this.equals(position);
	}

	public Column getColumn() {
		return column;
	}

	public Row getRow() {
		return row;
	}
}