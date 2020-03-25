package chess.domain.position;

import java.util.Objects;

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

	public boolean isSameRow(Position position) {
		return isSameRow(position.row);
	}

	public boolean isSameRow(Row row) {
		return this.row == row;
	}

	public boolean isSameCol(Position position) {
		return this.col == position.col;
	}

	public boolean isInDiagonal(Position position) {
		return this.row.calculateDistance(position.row) == this.col.calculateDistance(position.col);
	}

	public boolean isLinear(Position position){
		return isSameRow(position) || isSameCol(position) || isInDiagonal(position);
	}

	public int calculateMaxDistance(Position target) {
		return Integer.max(this.col.calculateDistance(target.col), this.row.calculateDistance(target.row));
	}

	public boolean isInDistance(int distance, Position position) {
		return calculateMaxDistance(position) <= distance;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Position position = (Position)o;
		return col == position.col &&
				row == position.row;
	}

	@Override
	public int hashCode() {
		return Objects.hash(col, row);
	}
}
