package chess.domain;

import java.util.Objects;

import chess.domain.chesspiece.Direction;

public class Position {
	private final Coordinate x;
	private final Coordinate y;

	public Position(int x, int y) {
		this(new Coordinate(x), new Coordinate(y));
	}

	public Position(int x, char y) {
		this(new Coordinate(x), new Coordinate(y));
	}

	public Position(Coordinate x, Coordinate y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Position{" +
			"x=" + x.getCoordinate() +
			", y=" + y.getCoordinate() +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Position position = (Position)o;
		return Objects.equals(x, position.x) &&
			Objects.equals(y, position.y);
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	public int getX() {
		return x.getCoordinate();
	}

	public int getY() {
		return y.getCoordinate();
	}
}
