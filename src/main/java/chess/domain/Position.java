package chess.domain;

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

	public Direction compare(Position destination) {
		return Direction.of(destination.x - this.x, destination.y - this.y);
	}
}
