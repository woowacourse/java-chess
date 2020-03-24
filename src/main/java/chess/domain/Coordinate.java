package chess.domain;

public class Coordinate {
	private final int coordinate;

	public Coordinate(int coordinate) {
		if (coordinate < 1 || coordinate > 8) {
			throw new IllegalArgumentException();
		}
		this.coordinate = coordinate;
	}

	public Coordinate(char coordinate) {
		if (coordinate < 'a' || coordinate > 'h') {
			throw new IllegalArgumentException();
		}
		this.coordinate = coordinate;
	}
}
