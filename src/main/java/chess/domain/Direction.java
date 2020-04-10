package chess.domain;

import java.util.Arrays;
import java.util.List;

import chess.domain.position.Position;

public enum Direction {
	NORTH(0, 1),
	NORTHEAST(1, 1),
	EAST(1, 0),
	SOUTHEAST(1, -1),
	SOUTH(0, -1),
	SOUTHWEST(-1, -1),
	WEST(-1, 0),
	NORTHWEST(-1, 1),

	NNE(1, 2),
	NNW(-1, 2),
	SSE(1, -2),
	SSW(-1, -2),
	EEN(2, 1),
	EES(2, -1),
	WWN(-2, 1),
	WWS(-2, -1);

	public static final List<Direction> BLACK_PAWN_DIRECTION = Arrays.asList(SOUTH, SOUTHEAST, SOUTHWEST);
	public static final List<Direction> WHITE_PAWN_DIRECTION = Arrays.asList(NORTH, NORTHEAST, NORTHWEST);
	public static final List<Direction> KNIGHT_DIRECTION = Arrays.asList(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS);
	public static final List<Direction> EVERY_DIRECTION = Arrays.asList(NORTH, EAST, SOUTH, WEST, NORTHEAST, SOUTHEAST,
		SOUTHWEST, NORTHWEST);
	public static final List<Direction> DIAGONAL_DIRECTION = Arrays.asList(NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
	public static final List<Direction> LINEAR_DIRECTION = Arrays.asList(NORTH, EAST, SOUTH, WEST);

	private int xDegree;
	private int yDegree;

	Direction(int xDegree, int yDegree) {
		this.xDegree = xDegree;
		this.yDegree = yDegree;
	}

	public static Direction getDirection(Position source, Position target) {
		int rowDifference = source.minusRow(target);
		int columnDifference = source.minusColumn(target);
		double tangent = rowDifference / (double)columnDifference;

		return Arrays.stream(values())
			.filter(dir -> isSameTangent(dir, tangent))
			.filter(dir -> dir.xDegree * columnDifference >= 0 && dir.yDegree * rowDifference >= 0)
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("해당 방향으로 이동할 수 없습니다."));
	}

	private static boolean isSameTangent(Direction dir, double tangent) {
		return (dir.yDegree / (double)dir.xDegree) == tangent;
	}

	public static List<Direction> findPawnDirectionBy(Team team) {
		if (team == Team.WHITE) {
			return WHITE_PAWN_DIRECTION;
		}
		return BLACK_PAWN_DIRECTION;
	}

	public int getXDegree() {
		return xDegree;
	}

	public int getYDegree() {
		return yDegree;
	}
}
