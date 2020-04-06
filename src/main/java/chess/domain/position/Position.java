package chess.domain.position;

import java.util.Arrays;
import java.util.Objects;

import chess.domain.Direction;

public class Position implements Comparable<Position> {
	private final Column column;
	private final Row row;

	public Position(Column column, Row row) {
		this.column = column;
		this.row = row;
	}

	public static Position of(String key) {
		return Arrays.stream(Column.values())
			.flatMap(column -> Arrays.stream(Row.values())
				.filter(row -> (column.getName() + row.getName()).equalsIgnoreCase(key))
				.map(row -> new Position(column, row))
			)
			.findFirst()
			.orElseThrow(IllegalArgumentException::new);
	}

	public static Position of(int x, int y) {
		return of((char)(x + 96) + String.valueOf(y));
	}

	public int minusRow(Position target) {
		return this.row.minus(target.row);
	}

	public int minusColumn(Position target) {
		return this.column.minus(target.column);
	}

	public Position plusDirection(Direction direction) {
		int x = direction.getXDegree();
		int y = direction.getYDegree();
		return new Position(column.plus(x), row.plus(y));
	}

	public boolean equalsX(int x) {
		return this.column.intValue() == x;
	}

	public Column getColumn() {
		return column;
	}

	public Row getRow() {
		return row;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Position))
			return false;
		Position position = (Position)o;
		return getColumn() == position.getColumn() &&
			getRow() == position.getRow();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getColumn(), getRow());
	}

	@Override
	public int compareTo(Position position) {
		if (row.intValue() > position.row.intValue()) {
			return -1;
		}
		if (row.intValue() < position.row.intValue()) {
			return 1;
		}
		return column.intValue() - position.column.intValue();
	}
}
