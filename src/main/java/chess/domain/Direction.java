package chess.domain;

import java.util.Arrays;

public enum Direction {
	NORTH(0, 1),
	WEST(-1, 0),
	EAST(1, 0),
	SOUTH(0, -1),

	NORTHNORTH(0, 2),
	SOUTHSOUTH(0, -2),

	NORTHEAST(1, 1),
	NORTHWEST(-1, 1),
	SOUTHEAST(1, -1),
	SOUTHWEST(-1, -1),

	NNE(1, 2),
	NEE(2, 1),
	NNW(-1, 2),
	NWW(-2, 1),
	SSE(1, -2),
	SEE(2, -1),
	SSW(-1, -2),
	SWW(-2, -1);

	private int xDegree;
	private int yDegree;

	Direction(int xDegree, int yDegree) {
		this.xDegree = xDegree;
		this.yDegree = yDegree;
	}

	public static Direction of(int x, int y) {
		return Arrays.stream(Direction.values()).filter(d -> d.xDegree == x && d.yDegree == y)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("올바르지 않은 방향입니다."));
	}

	public int getYDegree() {
		return yDegree;
	}

	public int getXDegree() {
		return xDegree;
	}
}
