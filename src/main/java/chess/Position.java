package chess;

import java.util.Objects;

public class Position {
	private final Coordinate x;
	private final Coordinate y;

	public Position(final int x, final int y) {
		this.x = new Coordinate(x);
		this.y = new Coordinate(y);
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
