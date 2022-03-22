package chess.domain;

import java.util.Objects;

public class Position {

	private final Row row;
	private final Column column;

	public Position(Row row, Column column) {
		this.row = row;
		this.column = column;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof Position)) {
			return false;
		}
		Position position = (Position)o;
		return Objects.equals(row, position.row) && Objects.equals(column, position.column);
	}

	@Override
	public int hashCode() {
		return Objects.hash(row, column);
	}
}
