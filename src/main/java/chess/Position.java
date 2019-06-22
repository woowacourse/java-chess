package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Position {
	private static final int MIN_BOUND = 1;
	private static final int MAX_BOUND = 8;
	private static final List<Position> positions = new ArrayList<>();

	private final Coordinate x;
	private final Coordinate y;

	static {
		for (int i = MIN_BOUND; i <= MAX_BOUND; i++) {
			for (int j = MIN_BOUND; j <= MAX_BOUND; ++j) {
				positions.add(new Position(Coordinate.getCoordinate(i), Coordinate.getCoordinate(j)));
			}
		}
	}

	private Position(final Coordinate x, final Coordinate y) {
		this.x = x;
		this.y = y;
	}

	public static Position getPosition(final int x, final int y) {
		return positions.stream()
				.filter(position -> position.x.isSame(x) && position.y.isSame(y))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("해당 위치를 찾을 수 없습니다."))
				;
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
