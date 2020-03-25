package chess.domain.position;

import java.util.function.BiPredicate;
import java.util.stream.Stream;

public enum Direction {
	UP(0, 1, (column, row) -> column == 0 && row > 0),
	DOWN(0, -1, (column, row) -> column == 0 && row < 0),
	LEFT(-1, 0, (column, row) -> column < 0 && row == 0),
	RIGHT(1, 0, (column, row) -> column > 0 && row == 0),
	LEFT_UP(-1, 1, (column, row) -> column < 0 && row > 0),
	LEFT_DOWN(-1, -1, (column, row) -> column < 0 && row < 0),
	RIGHT_UP(1, 1, (column, row) -> column > 0 && row > 0),
	RIGHT_DOWN(1, -1, (column, row) -> column > 0 && row < 0);

	private final int columnDirection;
	private final int rowDirection;
	private final BiPredicate<Integer, Integer> match;

	Direction(int columnDirection, int rowDirection,
		BiPredicate<Integer, Integer> match) {
		this.columnDirection = columnDirection;
		this.rowDirection = rowDirection;
		this.match = match;
	}

	public static Direction of(int columnDirection, int rowDirection) {
		return Stream.of(values())
			.filter(direction -> direction.match.test(columnDirection, rowDirection))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("방향이 존재하지 않습니다."));
	}

	public int getColumnDirection() {
		return columnDirection;
	}

	public int getRowDirection() {
		return rowDirection;
	}

	@Override
	public String toString() {
		return "Direction{" +
			"columnDirection=" + columnDirection +
			", rowDirection=" + rowDirection +
			'}';
	}
}
