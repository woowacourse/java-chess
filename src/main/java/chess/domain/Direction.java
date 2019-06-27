package chess.domain;

import java.util.Arrays;
import java.util.List;

public enum Direction {
	LEFT_TOP(-1, 1),
	TOP(0, 1),
	RIGHT_TOP(1, 1),
	LEFT(-1, 0),
	RIGHT(1, 0),
	LEFT_BOTTOM(-1, -1),
	BOTTOM(0, -1),
	RIGHT_BOTTOM(1, -1),

	LEFT_LEFT_TOP(-2, 1),
	LEFT_LEFT_BOTTOM(-2, -1),
	TOP_TOP_LEFT(-1, 2),
	TOP_TOP_RIGHT(1, 2),
	RIGHT_RIGHT_TOP(2, 1),
	RIGHT_RIGHT_BOTTOM(2, -1),
	BOTTOM_BOTTOM_RIGHT(1, -2),
	BOTTOM_BOTTOM_LEFT(-1, -2);

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

	public static List<Direction> getKingDirection() {
		return Arrays.asList(LEFT, LEFT_TOP, TOP, RIGHT_TOP, RIGHT, RIGHT_BOTTOM, BOTTOM, LEFT_BOTTOM);
	}

	public static List<Direction> getBishopDirection() {
		return Arrays.asList(LEFT_TOP, RIGHT_TOP, RIGHT_BOTTOM, LEFT_BOTTOM);
	}

	public static List<Direction> getKnightDirection() {
		return Arrays.asList(LEFT_LEFT_BOTTOM, LEFT_LEFT_TOP, TOP_TOP_LEFT, TOP_TOP_RIGHT,
				RIGHT_RIGHT_TOP, RIGHT_RIGHT_BOTTOM, BOTTOM_BOTTOM_RIGHT, BOTTOM_BOTTOM_LEFT);
	}

	public static Direction getWhitePawnMoveDirection() {
		return TOP;
	}

	public static Direction getBlackPawnMoveDirection() {
		return BOTTOM;
	}

	public static List<Direction> getWhitePawnAttackDirection() {
		return Arrays.asList(LEFT_TOP, RIGHT_TOP);
	}

	public static List<Direction> getBlackPawnAttackDirection() {
		return Arrays.asList(LEFT_BOTTOM, RIGHT_BOTTOM);
	}

	public static List<Direction> getQueenDirection(){
		return Arrays.asList(LEFT, LEFT_TOP, TOP, RIGHT_TOP, RIGHT, RIGHT_BOTTOM, BOTTOM, LEFT_BOTTOM);
	}

	public static List<Direction> getRookDirection() {
		return Arrays.asList(LEFT, TOP, RIGHT, BOTTOM);
	}
}