package domain.piece.position;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import domain.board.Board;

class PositionCache {
	private static final Map<String, Position> POSITION_CACHE = new HashMap<>();

	static {
		for (final Column column : Column.values()) {
			createByRow(column);
		}
	}

	private static void createByRow(Column column) {
		for (int i = Board.MIN_ROW_COUNT; i <= Board.MAX_ROW_COUNT; i++) {
			POSITION_CACHE.put(toKey(column, i), new Position(column, i));
		}
	}

	private static String toKey(Column column, int row) {
		return column.getColumnName() + row;
	}

	public static Position of(String inputPosition) {
		Position position = POSITION_CACHE.get(inputPosition);

		if (Objects.isNull(position)) {
			throw new InvalidPositionException(InvalidPositionException.INVALID_BOUNDARY_POSITION);
		}
		return position;
	}
}