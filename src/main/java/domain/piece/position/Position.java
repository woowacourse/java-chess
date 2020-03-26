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
		return this.column.getNumber() - targetPosition.getColumn().getNumber();
	}

	public int calculateRowGap(Position targetPosition) {
		return this.row - targetPosition.getRow();
	}

	public Column getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}
}
