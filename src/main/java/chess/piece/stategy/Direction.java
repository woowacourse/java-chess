package chess.piece.stategy;
//
// move b2 b3
// 	RNBQKBNR
// 	PPPPPPPP
// 	........
// 	........
// 	........
// 	.p......
// 	p.pppppp
// 	rnbqkbnr

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.team.Team;

public enum Direction {
	NORTH(0, 1),
	NORTH_EAST(1, 1),
	EAST(1, 0),
	SOUTH_EAST(1, -1),
	SOUTH(0, -1),
	SOUTH_WEST(-1, -1),
	WEST(-1, 0),
	NORTH_WEST(-1, 1),

	NORTH_NORTH(0, 2),
	SOUTH_SOUTH(0, -2),

	NORTH_NORTH_EAST(1, 2),
	NORTH_NORTH_WEST(-1, 2),
	SOUTH_SOUTH_EAST(1, -2),
	SOUTH_SOUTH_WEST(-1, -2),
	EAST_EAST_NORTH(2, 1),
	EAST_EAST_SOUTH(2, -1),
	WEST_WEST_NORTH(-2, 1),
	WEST_WEST_SOUTH(-2, -1);

	public static final List<Direction> LINEAR_DIRECTION = linearDirection();
	public static final List<Direction> DIAGONAL_DIRECTION = diagonalDirection();
	public static final List<Direction> EVERY_DIRECTION = everyDirection();
	public static final List<Direction> KNIGHT_DIRECTION = knightDirection();
	public static final Map<Team, List<Direction>> PAWN_INITIAL_DIRECTION = new HashMap<>() {
		{
			put(Team.BLACK, pawnDirection(Team.BLACK));
			put(Team.WHITE, pawnDirection(Team.WHITE));
		}
	};

	private final int xDegree;
	private final int yDegree;

	Direction(int xDegree, int yDegree) {
		this.xDegree = xDegree;
		this.yDegree = yDegree;
	}

	private static List<Direction> linearDirection() {
		return Arrays.asList(NORTH, EAST, SOUTH, WEST);
	}

	private static List<Direction> diagonalDirection() {
		return Arrays.asList(NORTH_EAST, SOUTH_EAST, SOUTH_WEST, NORTH_WEST);
	}

	private static List<Direction> everyDirection() {
		return Arrays.asList(NORTH, EAST, SOUTH, WEST, NORTH_EAST, SOUTH_EAST, SOUTH_WEST, NORTH_WEST);
	}

	private static List<Direction> knightDirection() {
		return Arrays.asList(NORTH_NORTH_EAST,
			NORTH_NORTH_WEST,
			SOUTH_SOUTH_EAST,
			SOUTH_SOUTH_WEST,
			EAST_EAST_NORTH,
			EAST_EAST_SOUTH,
			WEST_WEST_NORTH,
			WEST_WEST_SOUTH);
	}

	private static List<Direction> pawnDirection(Team team) {
		if (team == Team.BLACK) {
			return Arrays.asList(SOUTH, SOUTH_EAST, SOUTH_WEST, SOUTH_SOUTH);
		}
		return Arrays.asList(NORTH, NORTH_EAST, NORTH_WEST, NORTH_NORTH);
	}

	public int getXDegree() {
		return xDegree;
	}

	public int getYDegree() {
		return yDegree;
	}
}
