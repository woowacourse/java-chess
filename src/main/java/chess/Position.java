package chess;

import java.util.Objects;

public class Position {
	private final Coordinate x;
	private final Coordinate y;

	public Position(final int x, final int y) {
		this.x = new Coordinate(x);
		this.y = new Coordinate(y);
	}

	private Position(final Coordinate x, final Coordinate y) {
		this.x = x;
		this.y = y;
	}

	public Position move(Direction direction) {
		return new Position(x.move(direction.getDirectionX()), y.move(direction.getDirectionY()));
	}

	public boolean canMove(Position end, Direction direction) {
		Coordinate currentX = x;
		Coordinate currentY = y;

		while (currentX.canMove(direction.getDirectionX()) &&
				currentY.canMove(direction.getDirectionY())) {
			currentX = currentX.move(direction.getDirectionX());
			currentY = currentY.move(direction.getDirectionY());
			if (end.isSamePosition(currentX, currentY)) {
				return true;
			}
		}
		return false;
	}

	private boolean isSamePosition(Coordinate x, Coordinate y) {
		return (this.x.equals(x)) && (this.y.equals(y));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Position)) {
			return false;
		}
		final Position position = (Position) o;
		return Objects.equals(x, position.x) &&
				Objects.equals(y, position.y);
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
}
