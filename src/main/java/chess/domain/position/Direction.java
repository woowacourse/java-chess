package chess.domain.position;

import java.util.Arrays;
import java.util.List;

public enum Direction {
	UP(-1, 0),
	DOWN(1, 0),
	LEFT(0, -1),
	RIGHT(0, 1),

	UP_LEFT(-1, -1),
	UP_RIGHT(-1, 1),
	DOWN_LEFT(1, -1),
	DOWN_RIGHT(1, 1),

	UP_UP_LEFT(-2, -1),
	UP_UP_RIGHT(-2, 1),
	UP_LEFT_LEFT(-1, -2),
	UP_RIGHT_RIGHT(-1, 2),
	DOWN_DOWN_LEFT(2, -1),
	DOWN_DOWN_RIGHT(2, 1),
	DOWN_LEFT_LEFT(1, -2),
	DOWN_RIGHT_RIGHT(1, 2);

	public static List<Direction> LINEAR_DIRECTIONS = Arrays.asList(UP, DOWN, LEFT, RIGHT);
	public static List<Direction> DIAGONAL_DIRECTIONS = Arrays.asList(UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT);
	public static List<Direction> KNIGHT_DIRECTIONS = Arrays.asList(UP_UP_LEFT, UP_UP_RIGHT, UP_LEFT_LEFT,
		UP_RIGHT_RIGHT, DOWN_DOWN_LEFT, DOWN_DOWN_RIGHT, DOWN_LEFT_LEFT, DOWN_RIGHT_RIGHT);
	public static List<Direction> WHITE_PAWN_DIRECTIONS = Arrays.asList(UP, UP_LEFT, UP_RIGHT);
	public static List<Direction> BLACK_PAWN_DIRECTIONS = Arrays.asList(DOWN, DOWN_LEFT, DOWN_RIGHT);

	private final int rank;
	private final int file;

	Direction(int rank, int file) {
		this.rank = rank;
		this.file = file;
	}

}
