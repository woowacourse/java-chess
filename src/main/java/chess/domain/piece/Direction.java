package chess.domain.piece;

import java.util.Arrays;
import java.util.List;

/**
 *    방향을 의미하는 enum입니다.
 *
 *    @author AnHyungJu, LeeHoBin
 */
public enum Direction {
	NORTH(1, 0),
	EAST(0, 1),
	SOUTH(-1, 0),
	WEST(0, -1),

	NORTH_EAST(1, 1),
	SOUTH_EAST(-1, 1),
	SOUTH_WEST(-1, -1),
	NORTH_WEST(1, -1),
	NORTH_NORTH(2, 0),
	SOUTH_SOUTH(-2, 0),

	NORTH_NORTH_EAST(2, 1),
	NORTH_NORTH_WEST(2, -1),
	SOUTH_SOUTH_EAST(-2, 1),
	SOUTH_SOUTH_WEST(-2, -1),
	EAST_EAST_NORTH(1, 2),
	EAST_EAST_SOUTH(-1, 2),
	WEST_WEST_NORTH(1, -2),
	WEST_WEST_SOUTH(-1, -2);

	private int x;
	private int y;

	Direction(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public static List<Direction> linearDirection() {
		return Arrays.asList(NORTH, EAST, SOUTH, WEST);
	}

	public static List<Direction> diagonalDirection() {
		return Arrays.asList(NORTH_EAST, SOUTH_EAST, NORTH_WEST, SOUTH_WEST);
	}

	public static List<Direction> everyDirection() {
		return Arrays.asList(NORTH_EAST, SOUTH_EAST, SOUTH_WEST, NORTH_WEST, EAST, WEST, SOUTH, NORTH);
	}

	public static List<Direction> knightDirection() {
		return Arrays.asList(NORTH_NORTH_EAST, NORTH_NORTH_WEST, SOUTH_SOUTH_EAST, SOUTH_SOUTH_WEST, EAST_EAST_NORTH,
			EAST_EAST_SOUTH, WEST_WEST_NORTH, WEST_WEST_SOUTH);
	}

	public static List<Direction> whitePawnDirection() {
		return Arrays.asList(NORTH, NORTH_EAST, NORTH_WEST);
	}

	public static List<Direction> whitePawnInitialDirection() {
		return Arrays.asList(NORTH_NORTH, NORTH, NORTH_EAST, NORTH_WEST);
	}

	public static List<Direction> blackPawnInitialDirection() {
		return Arrays.asList(SOUTH_SOUTH, SOUTH, SOUTH_EAST, SOUTH_WEST);
	}

	public static List<Direction> blackPawnDirection() {
		return Arrays.asList(SOUTH, SOUTH_EAST, SOUTH_WEST);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
