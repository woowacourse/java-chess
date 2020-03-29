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
		return targetPosition.getColumn().getNumber() - this.column.getNumber();
	}

	public int calculateRowGap(Position targetPosition) {
		return targetPosition.getRow() - this.row;
	}

	public boolean isSamePosition(Position position) {
		return this.getColumn().getNumber() == position.getColumn().getNumber() &&
			this.getRow() == position.getRow();
	}

	public Column getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}
}