package chess.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Coordinate {
	public static final int MIN_BOUND = 1;
	public static final int MAX_BOUND = 8;
	private static final List<Coordinate> coordinates = new ArrayList<>();

	private final int coordinate;

	static {
		for (int i = MIN_BOUND; i <= MAX_BOUND; ++i) {
			coordinates.add(new Coordinate(i));
		}
	}

	private Coordinate(final int coordinate) {
		this.coordinate = coordinate;
	}

	public int getValue() {
		return coordinate;
	}

	public static Coordinate getCoordinate(final int coordinate) {
		if (coordinate < MIN_BOUND || coordinate > MAX_BOUND) {
			throw new IllegalArgumentException("좌표 범위를 벗어났습니다.");
		}
		return coordinates.get(coordinate - 1);
	}

	public Coordinate move(int direction) {
		return new Coordinate(coordinate + direction);
	}

	public boolean canMove(int direction) {
		int nextCoordinate = coordinate + direction;
		return nextCoordinate >= MIN_BOUND && nextCoordinate <= MAX_BOUND;
	}

	public boolean isSame(int coordinate) {
		return this.coordinate == coordinate;
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

	@Override
	public String toString() {
		return "Coordinate{" +
				"coordinate=" + coordinate +
				'}';
	}
}
