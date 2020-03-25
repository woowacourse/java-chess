package domain.piece.position;

public class Position {
	private Column column;
	private int row;

	Position(Column column, int row) {
		this.column = column;
		this.row = row;
	}

	public Column getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}
}
