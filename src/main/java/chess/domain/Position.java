package chess.domain;

import java.util.*;

public class Position implements Comparable<Position> {
	private static final Map<String, Position> POSITIONS = new TreeMap<>();

	private static final int DIAGONAL_INCLINATION = 1;
	private static final int RIGHT_INCLINATION = 0;
	private static final int ZERO = 0;

	static {
		for (final Column column : Column.values()) {
			for (final Row row : Row.values()) {
				String key = column.toString() + row.toString();
				POSITIONS.put(key, new Position(row, column));
			}
		}
	}

	private final Row row;
	private final Column column;

	private Position(final Row row, final Column column) {
		this.row = row;
		this.column = column;
	}

	public static Position of(final Row row, final Column column) {
		String key = column.toString() + row.toString();
		return POSITIONS.get(key);
	}

	public static Position of(final String row, final String column) {
		return Position.of(Row.from(row), Column.from(column));
	}

	public boolean isDiagonal(final Position target) {
		int height = this.row.calculateAbsolute(target.row);
		int width = this.column.calculateAbsolute(target.column);
		if (width == ZERO) {
			return false;
		}
		return Math.abs(height / width) == DIAGONAL_INCLINATION;
	}

	public boolean isPerpendicular(final Position target) {
		return this.column.calculateAbsolute(target.column) == RIGHT_INCLINATION;
	}

	public boolean isLevel(final Position target) {
		return this.row.calculateAbsolute(target.row) == RIGHT_INCLINATION;
	}

	public boolean isSameSumOfPosition(final Position position, int distance) {
		return sumRowAndColumn(position) == distance;
	}

	private int sumRowAndColumn(final Position target) {
		return Math.abs(this.distanceOfRow(target) + this.distanceOfColumn(target));
	}

	public boolean isSameSubOfPosition(final Position position, int distance) {
		return subRowAndColumn(position) == distance;
	}

	private int subRowAndColumn(final Position target) {
		return Math.abs(this.distanceOfRow(target) - this.distanceOfColumn(target));
	}

	private int distanceOfRow(final Position target) {
		return this.row.calculateAbsolute(target.row);
	}

	private int distanceOfColumn(final Position target) {
		return this.column.calculateAbsolute(target.column);
	}

	public List<Position> findRoutes(Position target) {
		if (!(isLevel(target) || isDiagonal(target) || isPerpendicular(target))) {
			return Collections.emptyList();
		}

		int gapOfRow = this.row.calculateSubtraction(target.row);
		int gapOfColumn = this.column.calculateSubtraction(target.column);

		int deltaOfRow = Integer.compare(0, gapOfRow);
		int deltaOfColumn = Integer.compare(0, gapOfColumn);

		List<Position> positions = new ArrayList<>();

		Position current = Position.of(this.row.next(deltaOfRow), this.column.next(deltaOfColumn));

		while (!current.equals(target)) {
			positions.add(current);
			current = Position.of(current.row.next(deltaOfRow), current.column.next(deltaOfColumn));
		}
		return positions;
	}

	public int vectorOfRow(final Position target) {
		return target.row.vectorOf(this.row);
	}

	public boolean isSameColumn(final Column other) {
		return this.column.equals(other);
	}

	@Override
	public int compareTo(final Position o) {
		int result = this.row.compareTo(o.row);

		if (result == 0) {
			result = this.column.compareTo(o.column);
		}

		return result;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final Position position = (Position) o;
		return Objects.equals(row, position.row) &&
				Objects.equals(column, position.column);
	}

	@Override
	public int hashCode() {
		return Objects.hash(row, column);
	}

	@Override
	public String toString() {
		return column.toString().concat(row.toString());
	}
}
