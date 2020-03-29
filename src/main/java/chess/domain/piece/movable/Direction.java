package chess.domain.piece.movable;

public enum Direction {
	NORTH(0, 1),
	NORTHEAST(1, 1),
	EAST(1, 0),
	SOUTHEAST(1, -1),
	SOUTH(0, -1),
	SOUTHWEST(-1, -1),
	WEST(-1, 0),
	NORTHWEST(-1, 1),

	// Knight가 이동 가능한 방향
	NNE(1, 2), //북북동
	NNW(-1, 2), //북북서
	SSE(1, -2), //남남동
	SSW(-1, -2), //남남서
	EEN(2, 1), //동동북
	EES(2, -1), //동동남
	WWN(-2, 1), //서서북
	WWS(-2, -1), //서서남

	NONE(0, 0); //방향이 없음

	private int xDegree;
	private int yDegree;

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
