package chess.domain.position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Position {
	private static final Map<String, Position> POSITIONS = new HashMap<>();

	static {
		for (Column column : Column.values()) {
			putByRow(column);
		}
	}

	private static void putByRow(Column column) {
		for (Row row : Row.values()) {
			POSITIONS.put(key(column, row), new Position(column, row));
		}
	}

	private final Column column;
	private final Row row;

	private Position(Column column, Row row) {
		this.column = column;
		this.row = row;
	}

	public static Position of(Column column, Row row) {
		return POSITIONS.get(key(column, row));
	}

	public static Position of(String position) {
		validate(position);
		return POSITIONS.get(position);
	}

	private static void validate(String position) {
		if (Objects.isNull(POSITIONS.get(position))) {
			throw new IllegalArgumentException("잘못된 위치값입니다.");
		}
	}

	private static String key(Column column, Row row) {
		return column.getName() + row.getName();
	}

	public Position up() {
		return Position.of(column, row.plus());
	}

	public Position down() {
		return Position.of(column, row.minus());
	}

	public Position right() {
		return Position.of(column.plus(), row);
	}

	public Position left() {
		return Position.of(column.minus(), row);
	}

	public boolean isColumnEquals(Position that) {
		return column.equals(that.column);
	}

	public boolean isRowEquals(Position that) {
		return isRowEquals(that.row);
	}

	public boolean isRowEquals(Row row) {
		return this.row.equals(row);
	}

	public int getColumnGap(Position that) {
		return column.compareTo(that.column);
	}

	public int getRowGap(Position that) {
		return row.compareTo(that.row);
	}

	public Column getColumn() {
		return column;
	}

	public static List<Position> getPositions() {
		return List.copyOf(POSITIONS.values());
	}
}