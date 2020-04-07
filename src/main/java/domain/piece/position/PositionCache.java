package domain.piece.position;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class PositionCache {
	private static final Map<String, Position> POSITION_CACHE = new HashMap<>();

	static {
		for (final Column column : Column.values()) {
			createByRow(column);
		}
	}

	private static void createByRow(Column column) {
		for (Row row : Row.values()) {
			POSITION_CACHE.put(toKey(column, row), new Position(column, row));
		}
	}

	private static String toKey(Column column, Row row) {
		return column.getColumnName()+ row.getRow();
	}

	public static Position of(String inputPosition) {
		Position position = POSITION_CACHE.get(inputPosition);
		Objects.requireNonNull(position, InvalidPositionException.INVALID_BOUNDARY_POSITION);
		return position;
	}
}