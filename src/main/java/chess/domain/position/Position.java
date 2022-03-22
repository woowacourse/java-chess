package chess.domain.position;

public class Position {
	private final Row row;
	private final Column column;

	public Position(Row row, Column column) {
		this.row = row;
		this.column = column;
	}
}
