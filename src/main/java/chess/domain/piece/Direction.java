package chess.domain.piece;

import java.util.Arrays;
import java.util.List;

public enum Direction {
	NORTH(0, 1),
	SOUTH(0, -1),
	WEST(-1, 0),
	EAST(1, 0),

	NORTH_WEST(-1, 1),
	NORTH_EAST(1, 1),
	SOUTH_WEST(-1, -1),
	SOUTH_EAST(1, -1),

	NORTH_NORTH_EAST(1, 2),
	NORTH_NORTH_WEST(-1, 2),
	SOUTH_SOUTH_EAST(1, -2),
	SOUTH_SOUTH_WEST(-1, -2),
	EAST_EAST_NORTH(2, 1),
	EAST_EAST_SOUTH(2, -1),
	WEST_WEST_NORTH(-2, 1),
	WEST_WEST_SOUTH(-2, -1);

	public static final List<Direction> LINEAR_DIRECTION = Arrays.asList(NORTH, EAST, SOUTH, WEST);
	public static final List<Direction> DIAGONAL_DIRECTION = Arrays.asList(NORTH_EAST, NORTH_WEST, SOUTH_EAST,
			SOUTH_WEST);
	public static final List<Direction> EVERY_DIRECTION = Arrays.asList(NORTH, EAST, SOUTH, WEST, NORTH_EAST,
			NORTH_WEST, SOUTH_EAST, SOUTH_WEST);
	public static final List<Direction> KNIGHT_DIRECTION = Arrays.asList(NORTH_NORTH_EAST, NORTH_NORTH_WEST,
			SOUTH_SOUTH_EAST, SOUTH_SOUTH_WEST, EAST_EAST_NORTH, EAST_EAST_SOUTH, WEST_WEST_NORTH, WEST_WEST_SOUTH);
	public static final List<Direction> WHITE_PAWN_DIRECTION = Arrays.asList(NORTH_EAST, NORTH_WEST, NORTH);
	public static final List<Direction> BLACK_PAWN_DIRECTION = Arrays.asList(SOUTH_EAST, SOUTH_WEST, SOUTH);

	private final int x;
	private final int y;

	Direction(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public static Direction of(int x, int y) {
		return Arrays.stream(values())
				.filter(direction -> direction.x == x && direction.y == y)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("유효하지 않은 방향입니다."));
	}

	public static Direction ofDiagonal(int x, int y) {
		if (Math.abs(x) - Math.abs(y) != 0) {
			throw new IllegalArgumentException("대각선 방향이 아닙니다.");
		}
		return of(Integer.compare(x, 0), Integer.compare(y, 0));
	}

	public static Direction ofLinear(int x, int y) {
		if (Math.abs(x - y) != Math.abs(x + y)) {
			throw new IllegalArgumentException("직선 방향이 아닙니다.");
		}
		return of(Integer.compare(x, 0), Integer.compare(y, 0));
	}

	public static Direction ofEvery(int x, int y) {
		if (Math.abs(x) - Math.abs(y) != 0 && Math.abs(x - y) != Math.abs(x + y)) {
			throw new IllegalArgumentException("8방향이 아닙니다.");
		}
		return of(Integer.compare(x, 0), Integer.compare(y, 0));
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
