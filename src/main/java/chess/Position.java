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

	public Position(Position position) {
		this.x = position.x;
		this.y = position.y;
	}

	public int getMaxDistance(Direction direction) {
		int distance = 0;
		Coordinate currentX = x;
		Coordinate currentY = y;
		int directionX = direction.getDirectionX();
		int directionY = direction.getDirectionY();

		while (currentX.canMove(directionX)
				&& currentY.canMove(directionY)) {
			distance++;
			currentX = currentX.move(directionX);
			currentY = currentY.move(directionY);
		}
		return distance;
	}

	public Position move(Direction direction) {
		return new Position(this.x.move(direction.getDirectionX()),
				this.y.move(direction.getDirectionY()));
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

	@Override
	public String toString() {
		return "{" + x + ", " + y + "}\n";
	}
}
