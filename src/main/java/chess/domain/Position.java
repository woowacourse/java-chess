package chess.domain;

import static java.lang.Math.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import chess.exception.PositionOutOfBoundsException;

public class Position {
	private final int x;
	private final int y;

	public Position(int x, int y) {
		validatePositionRange(x, y);
		this.x = x;
		this.y = y;
	}

	public Position(String string) {
		int x, y;
		try {
			x = string.charAt(0) - 'a' + 1;
			y = Character.getNumericValue(string.charAt(1));
		} catch (Exception e) {
			throw new IllegalArgumentException("올바른 위치값을 입력해 주십시오.");
		}
		validatePositionRange(x, y);
		this.x = x;
		this.y = y;
	}

	private void validatePositionRange(int x, int y) {
		if (x < 1 || x > 8 || y < 1 || y > 8) {
			throw new PositionOutOfBoundsException("올바른 좌표 값이 아닙니다.");
		}
	}

	public Position move(Direction direction) {
		return move(direction, 1);
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

	public boolean isInSameColumn(Position position) {
		return this.x == position.x;
	}
}
