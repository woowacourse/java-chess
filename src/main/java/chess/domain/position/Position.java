package chess.domain.position;

import java.util.Arrays;
import java.util.Objects;

import chess.domain.Direction;

public class Position {
	private Column col;
	private Row row;

	public Position(String position) {
		this(position.substring(0, 1), Integer.parseInt(position.substring(1)));
	}

	public Position(int col, int row) {
		this(Column.of(col), Row.of(row));
	}

	public Position(String col, Row row) {
		this(Column.of(col), row);
	}

	public Position(String col, int row) {
		this(Column.of(col), Row.of(row));
	}

	public Position(Column col, Row row) {
		this.col = col;
		this.row = row;
	}

	public Direction findDirection(Position target) {
		return Arrays.stream(Direction.values())
				.filter(strategy -> strategy.isSameDirection(this, target))
				.findAny()
				.orElse(Direction.NON_LINEAR);
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

	public boolean isSameCol(Column column) {
		return this.col == column;
	}

	public boolean isInDiagonal(Position position) {
		return isPositiveDiagonal(position) || isNegativeDiagonal(position);
	}

	public boolean isPositiveDiagonal(Position position) {
		return this.row.calculateDifference(position.row) == this.col.calculateDifference(position.col);
	}

	public boolean isNegativeDiagonal(Position position) {
		return this.row.calculateDifference(position.row) == this.col.calculateDifference(position.col) * -1;
	}

	public boolean isLinear(Position position) {
		return isSameRow(position) || isSameCol(position) || isInDiagonal(position);
	}

	public int calculateMaxDistance(Position target) {
		return Integer.max(this.col.calculateDistance(target.col), this.row.calculateDistance(target.row));
	}

	public boolean isInDistance(int distance, Position position) {
		return calculateMaxDistance(position) <= distance;
	}

	public int compareToRow(Position position) {
		return this.row.compareTo(position.row);
	}

	public int compareToCol(Position position) {
		return this.col.compareTo(position.col);
	}

	public Column getCol() {
		return col;
	}

	public Row getRow() {
		return row;
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

