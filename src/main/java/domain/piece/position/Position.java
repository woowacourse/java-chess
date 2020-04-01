package domain.piece.position;

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
		return PositionCache.of(column.getColumnName() + row.getNumber());
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