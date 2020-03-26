package chess.domain.piece.movable;

import chess.domain.piece.Color;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
	WWS(-2, -1),

	NORTHDOUBLE(0, 2),
	SOUTHDOUBLE(0, -2);


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

	public static List<Direction> linearDirection() {
		return Arrays.asList(NORTH, EAST, SOUTH, WEST);
	}

	public static List<Direction> diagonalDirection() {
		return Arrays.asList(NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
	}

	public static List<Direction> everyDirection() {
		return Arrays.asList(NORTH, EAST, SOUTH, WEST, NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
	}

	public static List<Direction> knightDirection() {
		return Arrays.asList(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS);
	}

	public static List<Direction> getPawnDirectionBy(Color color) {
		Objects.requireNonNull(color, "없는 색상입니다.");
		if (color.isWhite()) {
			return whitePawnDirection();
		}
		if (color.isBlack()) {
			return blackPawnDirection();
		}
		return Collections.emptyList();
	}

	public static List<Direction> whitePawnDirection() {
		return Arrays.asList(NORTH, NORTHEAST, NORTHWEST);
	}

	public static List<Direction> whiteInitialPawnDirection() {
		return Arrays.asList(NORTH, NORTHEAST, NORTHWEST, NORTHDOUBLE);
	}

	public static List<Direction> blackPawnDirection() {
		return Arrays.asList(SOUTH, SOUTHEAST, SOUTHWEST);
	}

	public static List<Direction> blackInitialPawnDirection() {
		return Arrays.asList(SOUTH, SOUTHEAST, SOUTHWEST, SOUTHDOUBLE);
	}
}
