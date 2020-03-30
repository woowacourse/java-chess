package domain.piece.position;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class PositionCache {
	private static final int ROW_START_INDEX = 1;
	private static final int ROW_END_INDEX = 8;
	private static final Map<String, Position> POSITION_CACHE = new HashMap<>();

	static {
		for (final Column column : Column.values()) {
			createByRow(column);
		}
	}

	private static void createByRow(Column column) {
		for (int i = ROW_START_INDEX; i <= ROW_END_INDEX; i++) {
			POSITION_CACHE.put(toKey(column, i), new Position(column, i));
		}
	}

	private static String toKey(Column column, int row) {
		return column.getColumnName() + String.valueOf(row);
	}

	public static Position of(String inputPosition) {
		if (Objects.isNull(POSITION_CACHE.get(inputPosition))) {
			throw new InvalidPositionException(InvalidPositionException.INVALID_BOUNDARY_POSITION);
		}
		return POSITION_CACHE.get(inputPosition);
	}
}
