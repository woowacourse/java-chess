package chess.domain.position;

public class Position {
	private Column col;
	private Row row;

	public Position(int col, int row) {
		this(Column.of(col), Row.of(row));
	}

	public Position(Column col, int row) {
		this(col, Row.of(row));
	}

	public Position(Column col, Row row) {
		this.col = col;
		this.row = row;
	}
}
