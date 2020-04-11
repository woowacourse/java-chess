package chess.domain.movepattern;

public enum Direction {
	UP(0, 1),
	DOWN(0, -1),
	LEFT(-1, 0),
	RIGHT(1, 0),
	UP_RIGHT(1, 1),
	UP_LEFT(-1, 1),
	DOWN_RIGHT(1, -1),
	DOWN_LEFT(-1, -1);

	private final int xDegree;
	private final int yDegree;

	Direction(int xDegree, int yDegree) {
		this.xDegree = xDegree;
		this.yDegree = yDegree;
	}

	public int getXDegree() {
		return xDegree;
	}

	public int getYDegree() {
		return yDegree;
	}
}
