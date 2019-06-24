package chess.domain;

public enum Direction {
	LEFT_TOP(-1, 1),
	TOP(0, 1),
	RIGHT_TOP(1, 1),
	LEFT(-1, 0),
	RIGHT(1, 0),
	LEFT_BOTTOM(-1, -1),
	BOTTOM(0, -1),
	RIGHT_BOTTOM(1, -1),

	LEFT_LEFT_TOP(-2, 1),
	LEFT_LEFT_BOTTOM(-2, -1),
	TOP_TOP_LEFT(-1, 2),
	TOP_TOP_RIGHT(1, 2),
	RIGHT_RIGHT_TOP(2, 1),
	RIGHT_RIGHT_BOTTOM(2, -1),
	BOTTOM_BOTTOM_RIGHT(1, -2),
	BOTTOM_BOTTOM_LEFT(-1, -2);

	private int directionX;
	private int directionY;

	Direction(int directionX, int directionY) {
		this.directionX = directionX;
		this.directionY = directionY;
	}

	public int getDirectionX() {
		return directionX;
	}

	public int getDirectionY() {
		return directionY;
	}
}
