package chess.domain;

import static java.lang.Math.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import chess.exception.PositionOutOfBoundsException;

public class Position {
	private static final int LOWER_BOUND = 1;
	private static final int UPPER_BOUND = 8;
	private static final String INVALID_POSITION = "올바른 위치값을 입력해 주십시오.";
	private final int x;
	private final int y;

	public Position(int x, int y) {
		validatePositionRange(x, y);
		this.x = x;
		this.y = y;
	}

	public Position(String string) {
		try {
			int x = alphabetToNumericValue(string.charAt(0));
			int y = numericCharacterToNumericValue(string.charAt(1));
			validatePositionRange(x, y);
			this.x = x;
			this.y = y;
		} catch (Exception e) {
			throw new IllegalArgumentException(INVALID_POSITION);
		}
	}

	private int numericCharacterToNumericValue(char character) {
		return Character.getNumericValue(character);
	}

	private int alphabetToNumericValue(char character) {
		return character - 'a' + 1;
	}

	private void validatePositionRange(int x, int y) {
		if (x < LOWER_BOUND || x > UPPER_BOUND || y < LOWER_BOUND || y > UPPER_BOUND) {
			throw new PositionOutOfBoundsException(INVALID_POSITION);
		}
	}

	public Position move(Direction direction, int distance) {
		return new Position(x + (direction.getXDegree() * distance), y + (direction.getYDegree() * distance));
	}

	public List<Position> getPositionsInBetween(Position destination) {
		List<Position> positionsInBetween = new ArrayList<>();
		int xDegree = destination.x - this.x;
		int yDegree = destination.y - this.y;
		int maxDegree = max(abs(xDegree), abs(yDegree));
		Direction direction = Direction.of(xDegree / maxDegree, yDegree / maxDegree);
		for (int i = 1; i < maxDegree; i++) {
			positionsInBetween.add(new Position(this.x, this.y).move(direction, i));
		}
		return positionsInBetween;
	}

	public Direction calculateDirection(Position destination) {
		return Direction.of(destination.x - this.x, destination.y - this.y);
	}

	public boolean isNonLinearDirection(Position destination) {
		return this.x != destination.x && this.y != destination.y;
	}

	public boolean isNonDiagonalDirection(Position destination) {
		return abs(destination.x - this.x) != abs(destination.y - this.y);
	}

	public boolean isPawnInOriginalPosition() {
		return this.y == 2 || this.y == 7;
	}

	public boolean isInSameColumn(Position position) {
		return this.x == position.x;
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
		return Character.toString((char)((int)'a'-1+x))+y;
	}
}
