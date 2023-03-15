package chess.domain;

import java.util.List;

public enum Direction {

	NORTH(0, 1),
	NORTH_NORTH_EAST(1, 2),
	NORTH_EAST(1, 1),
	NORTH_EAST_EAST(2, 1),
	EAST(1, 0),
	SOUTH_EAST_EAST(2, -1),
	SOUTH_EAST(1, -1),
	SOUTH_SOUTH_EAST(1, -2),
	SOUTH(0, -1),
	SOUTH_SOUTH_WEST(-1, -2),
	SOUTH_WEST(-1, -1),
	SOUTH_WEST_WEST(-2, -1),
	WEST(-1, 0),
	NORTH_WEST_WEST(-2, 1),
	NORTH_WEST(-1, 1),
	NORTH_NORTH_WEST(-1, 2);

	private final int x;
	private final int y;

	Direction(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	public static List<Direction> getAll() {
		return List.of(values());
	}

	public static List<Direction> getCross() {
		return List.of(NORTH, EAST, SOUTH, WEST);
	}

	public static List<Direction> getDiagonal() {
		return List.of(NORTH_EAST, SOUTH_EAST, SOUTH_WEST, NORTH_WEST);
	}

	public static List<Direction> getLShaped() {
		return List.of(NORTH_NORTH_EAST, NORTH_EAST_EAST, SOUTH_EAST_EAST, SOUTH_SOUTH_EAST, SOUTH_SOUTH_WEST,
			SOUTH_WEST_WEST, NORTH_WEST_WEST, NORTH_NORTH_WEST);
	}
	public static List<Direction> getNorthern() {
		return List.of(NORTH);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
