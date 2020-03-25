package chess.board;

import java.util.Objects;

public class Location {
	private final int row;
	private final char col;

	public Location(int row, char col) {
		this.row = row;
		this.col = col;
	}

	public Location moveTo(final int row, final char col) {
		return new Location(row, col);
	}

	public Location moveRowBy(final int rowValue) {
		return moveTo(this.row + rowValue, col);
	}

	public Location moveColBy(final int colValue) {
		return moveTo(row, (char)(this.col + colValue));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Location location = (Location)o;
		return row == location.row &&
			col == location.col;
	}

	@Override
	public String toString() {
		return "Location{" +
			"row=" + row +
			", col=" + col +
			'}';
	}

	@Override
	public int hashCode() {
		return Objects.hash(row, col);
	}
}
