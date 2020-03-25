package chess.domain.board;

import chess.domain.Direction;
import chess.domain.board.position.Column;
import chess.domain.board.position.Position;
import chess.domain.board.position.Row;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Board {
	private static final String INVALID_INPUT_EXCEPTION_MESSAGE = "옳지 않은 좌표 입력입니다.";
	private static final int MIN_BOUND = 1;
	private static final int MAX_BOUND = 8;

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
			positionCache.put(name, new Position(name));
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

	public static boolean checkBound(Position position, Direction direction) {
		Row row = position.getRow();
		Column column = position.getColumn();

		return isValidBound(row.getValue() + direction.getXDegree()) && isValidBound(column.getValue() + direction.getYDegree());
	}

	private static boolean isValidBound(int value) {
		return value >= MIN_BOUND && value <= MAX_BOUND;
	}
}
