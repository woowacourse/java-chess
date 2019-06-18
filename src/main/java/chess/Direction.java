package chess;

public enum  Direction {
	LEFT_TOP(-1, 1),
	TOP(0, 1),
	RIGHT_TOP(1, 1),
	LEFT(-1, 0),
	RIGHT(1, 0),
	LEFT_BOTTOM(-1, -1),
	BOTTOM(0, -1),
	RIGHT_BOTTOM(1, -1);

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
