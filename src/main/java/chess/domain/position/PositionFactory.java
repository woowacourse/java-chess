package chess.domain.position;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PositionFactory {
	private static final String INVALID_INPUT_EXCEPTION_MESSAGE = "옳지 않은 좌표 입력입니다.";

	private static final Map<String, Position> positionCache;

	static {
		positionCache = new HashMap<>();
		for (Row row : Row.values()) {
			createPositionBy(row);
		}
	}

	private static void createPositionBy(Row row) {
		for (Column column : Column.values()) {
			String name = row.getName() + column.getName();
			positionCache.put(name, new Position(row, column));
		}
	}

	public static Position of(String position) {
		validate(position);
		return positionCache.get(position);
	}

	public static Position of(Row row, Column column) {
		return of(row.getName() + column.getName());
	}

	private static void validate(String position) {
		Objects.requireNonNull(position, INVALID_INPUT_EXCEPTION_MESSAGE);
		if (position.isEmpty()) {
			throw new IllegalArgumentException(INVALID_INPUT_EXCEPTION_MESSAGE);
		}
		if (!positionCache.containsKey(position)) {
			throw new IllegalArgumentException(INVALID_INPUT_EXCEPTION_MESSAGE);
		}
	}

}
