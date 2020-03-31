package domain.piece.position;

public class Position {
	private Column column;
	private int row;

	Position(Column column, int row) {
		this.column = column;
		this.row = row;
	}

	public static Position of(String position) {
		return PositionCache.of(position);
	}

	public int calculateColumnGap(Position targetPosition) {
		return column.gap(targetPosition.column);
	}

	public int calculateRowGap(Position targetPosition) {
		return targetPosition.row - this.row;
	}

	public boolean isSamePosition(Position position) {
		return this.equals(position);
	}

	public Column getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}
}