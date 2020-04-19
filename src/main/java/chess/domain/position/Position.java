package chess.domain.position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import chess.domain.Direction;

public class Position {
	private static final int MIN_INT = 1;
	private static final int MAX_INT = 8;
	private static final char MIN_CHAR = 'a';
	private static final char MAX_CHAR = 'h';
	private static final int ALPHABET_DEFORMATION_VALUE = 96;
	private static final String NOT_ABLE_VALUE_MESSAGE = "허용되는 좌표 값이 아닙니다.";
	private static final int ALPHABET_INDEX = 0;
	private static final int INT_FROM_INDEX = 1;
	private static final int INT_TO_INDEX = 2;

	private final int x;
	private final int y;

	public static Position of(int x, int y) {
		validateIntRange(x);
		validateIntRange(y);
		return new Position(x, y);
	}

	public static Position of(int x, char y) {
		validateIntRange(x);
		validateCharRange(y);
		return new Position(x, y - ALPHABET_DEFORMATION_VALUE);
	}

	public static Position of(String position) {
		char alphabet = position.charAt(ALPHABET_INDEX);
		int number = Integer.parseInt(position.substring(INT_FROM_INDEX, INT_TO_INDEX));
		return Position.of(number, alphabet);
	}

	private static void validateIntRange(int num) {
		if (num < MIN_INT || num > MAX_INT) {
			throw new IllegalArgumentException(NOT_ABLE_VALUE_MESSAGE);
		}
	}

	private static void validateCharRange(char num) {
		if (num < MIN_CHAR || num > MAX_CHAR) {
			throw new IllegalArgumentException(NOT_ABLE_VALUE_MESSAGE);
		}
	}

	private Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Position plus(Direction direction) {
		return new Position(direction.plusX(x), direction.plusY(y));
	}

	public PositionGap calculateDiff(Position targetPosition) {
		return new PositionGap(targetPosition.x - this.x, targetPosition.y - this.y);
	}

	public Positions calculatePath(Position targetPosition, Direction direction) {
		int pathX = direction.plusX(x);
		int pathY = direction.plusY(y);
		List<Position> positions = new ArrayList<>();
		while (new Position(pathX, pathY).isNotEquals(targetPosition)) {
			positions.add(new Position(pathX, pathY));
			pathX = direction.plusX(pathX);
			pathY = direction.plusY(pathY);
		}
		return new Positions(positions);
	}

	private boolean isNotEquals(Position targetPosition) {
		return this.equals(targetPosition) == false;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Position position = (Position)o;
		return x == position.x &&
			y == position.y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public String toString() {
		return ((char)(y + ALPHABET_DEFORMATION_VALUE)) + "" + x;
	}
}