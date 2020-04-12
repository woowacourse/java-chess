package chess.domain.position;

import java.util.Objects;

public class Position {
	private static final int POSITION_STRING_LENGTH = 2;
	private Column col;
	private Row row;

	private Position(Column col, Row row) {
		this.col = col;
		this.row = row;
	}

	public static Position of(Column col, Row row) {
		return new Position(col, row);
	}

	public static Position of(String col, Row row) {
		return new Position(Column.of(col), row);
	}

	public static Position of(String col, int row) {
		return new Position(Column.of(col), Row.of(row));
	}

	public static Position of(int col, int row) {
		return new Position(Column.of(col), Row.of(row));
	}

	public static Position of(String col, String row) {
		return new Position(Column.of(col), Row.of(row));
	}

	public static Position of(String position) {
		validate(position);
		return of(position.substring(0, 1), position.substring(1));
	}

	private static void validate(String position) {
		if (Objects.isNull(position) || position.isEmpty()) {
			throw new IllegalArgumentException("빈 값을 입력하지 마세요.");
		}
		if (position.length() < POSITION_STRING_LENGTH) {
			throw new IllegalArgumentException("위치를 정확히 입력해주세요.");
		}
	}

	public Direction findDirection(Position target) {
		return Direction.findDirection(this, target);
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
		Position position = (Position) o;
		return col == position.col &&
				row == position.row;
	}

	@Override
	public int hashCode() {
		return Objects.hash(col, row);
	}
}

