package chess.domain;

import static java.lang.Math.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Position {
	private int x;
	private int y;

	public Position(int x, int y) {
		validatePositionRange(x, y);
		this.x = x;
		this.y = y;
	}

	public Position(String string) {
		this(string.charAt(0) - 'a' + 1, Character.getNumericValue(string.charAt(1)));
	}

	private void validatePositionRange(int x, int y) {
		if (x < 1 || x > 8 || y < 1 || y > 8) {
			throw new IllegalArgumentException("체스판 범위를 초과한 값입니다.");
		}
	}

	public Position move(Direction direction) {
		return new Position(x + direction.getXDegree(), y + direction.getYDegree());
	}

	public Map<Direction, Integer> inBetween(Position destination) {
		int xDegree = destination.x - this.x;
		int yDegree = destination.y - this.y;
		int maxDegree = max(abs(xDegree), abs(yDegree));
		Direction direction = Direction.of(xDegree / maxDegree, yDegree / maxDegree);
		Map<Direction, Integer> directionAndDistance = new HashMap<>();
		directionAndDistance.put(direction, maxDegree);
		return directionAndDistance;
	}

	public Direction calculateDirection(Position destination) {
		return Direction.of(destination.x - this.x, destination.y - this.y);
	}

	public boolean checkLinearDirection(Position destination) {
		return this.x == destination.x || this.y == destination.y;
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
}
