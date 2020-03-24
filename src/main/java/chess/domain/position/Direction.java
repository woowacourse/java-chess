package chess.domain.position;

public enum Direction {
	UP(0, 1),
	DOWN(0, -1),
	LEFT(-1, 0),
	RIGHT(1, 0),
	LEFT_UP(-1, 1),
	LEFT_DOWN(-1, -1),
	RIGHT_UP(1, 1),
	RIGHT_DOWN(1, -1);

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
}
