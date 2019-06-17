package chess;

import java.util.Objects;

public class Coordinate {
	private static final int MIN_BOUND = 1;
	private static final int MAX_BOUND = 8;

	private final int coordinate;

	public Coordinate(final int coordinate) {
		if (coordinate < MIN_BOUND || coordinate > MAX_BOUND) {
			throw new IllegalArgumentException("좌표 범위를 벗어났습니다.");
		}
		this.coordinate = coordinate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Coordinate)) {
			return false;
		}
		final Coordinate that = (Coordinate) o;
		return coordinate == that.coordinate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(coordinate);
	}
}
