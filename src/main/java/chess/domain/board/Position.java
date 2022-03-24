package chess.domain.board;

import java.util.Objects;

public class Position {

	private final Row row;
	private final Column column;

	public Position(final Row row, final Column column) {
		this.row = row;
		this.column = column;
	}

	public int calculateRowDifference(final Position target) {
		return row.calculateDifference(target.row);
	}

	public int calculateColumnDifference(final Position target) {
		return column.calculateDifference(target.column);
	}

	public Row getRow() {
		return row;
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
