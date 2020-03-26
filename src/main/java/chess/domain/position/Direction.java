package chess.domain.position;

public enum Direction {
	DOWN(0, 1),
	UP(0, -1),
	LEFT(-1, 0),
	RIGHT(1, 0),
	LEFT_DOWN(-1, 1),
	LEFT_UP(-1, -1),
	RIGHT_DOWN(1, 1),
	RIGHT_UP(1, -1),
	LEFT_LEFT_DOWN(-2, 1),
	LEFT_LEFT_UP(-2, -1),
	RIGHT_RIGHT_DOWN(2, 1),
	RIGHT_RIGHT_UP(2, -1),
	LEFT_DOWN_DOWN(-1, 2),
	LEFT_UP_UP(-1, -2),
	RIGHT_DOWN_DOWN(1, 2),
	RIGHT_UP_UP(1, -2);

	private final int columnDirection;
	private final int rowDirection;

	Direction(int columnDirection, int rowDirection) {
		this.columnDirection = columnDirection;
		this.rowDirection = rowDirection;
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
