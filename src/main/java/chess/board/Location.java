package chess.board;

import static java.lang.Math.*;

import java.util.Arrays;
import java.util.Objects;

// 팀별 초기위치를 갖고있는다.
public class Location {
	private final int row;
	private final char col;

	public Location(final int row, final char col) {
		this.row = row;
		this.col = col;
	}

	public Location(Location now) {
		this.row = now.row;
		this.col = now.col;
	}

	public boolean isSameCol(Location other) {
		return this.col == other.col;
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

	public boolean isDiagonal(Location destination) {
		return abs(row - destination.row) == abs(col - destination.col);
	}

	public boolean isKingRange(Location destination) {
		boolean rowFlag = abs(row - destination.row) <= 1;
		boolean colFlag = abs(col - destination.col) <= 1;

		return rowFlag && colFlag;
	}

	public boolean isKnightRange(Location destination) {
		int[] dx = {2, 2, 1, 1, -1, -1, -2, -2};
		int[] dy = {1, -1, -2, 2, -2, 2, -1, 1};

		for (int i = 0; i < dx.length; i++) {
			int nx = this.row + dx[i];
			int ny = this.col + dy[i];
			if (destination.row == nx && destination.col == ny) {
				return true;
			}
		}
		return false;
	}

	public boolean isQueenRang(Location destination) {
		return isDiagonal(destination) || isStraight(destination);
	}

	public boolean isStraight(Location destination) {
		return this.row == destination.row || isVertical(destination);
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

	public boolean isInitialPawnLocation(boolean black) {
		Location[] whiteTeamInitialPawnLocations = {
			new Location(2, 'a'),
			new Location(2, 'b'),
			new Location(2, 'c'),
			new Location(2, 'd'),
			new Location(2, 'e'),
			new Location(2, 'f'),
			new Location(2, 'g'),
			new Location(2, 'h')
		};
		Location[] blackTeamInitialPawnLocations = {
			new Location(7, 'a'),
			new Location(7, 'b'),
			new Location(7, 'c'),
			new Location(7, 'd'),
			new Location(7, 'e'),
			new Location(7, 'f'),
			new Location(7, 'g'),
			new Location(7, 'h')
		};
		if (black) {
			return Arrays.asList(blackTeamInitialPawnLocations)
				.contains(this);
		}
		return Arrays.asList(whiteTeamInitialPawnLocations)
			.contains(this);
	}

	public boolean isInitialPawnForwardRange(Location after, int value) {
		boolean result = row + value == after.row || row + (value * 2) == after.row;

		return result && col == after.col;
	}

	public boolean isPawnForwardRange(Location after, int value) {
		boolean result = row + value == after.row;

		return result && col == after.col;
	}

	public boolean isForwardDiagonal(Location after, int value) {
		return this.row + value == after.row
			&& this.col - 1 == after.col || this.col + 1 == after.col;
	}

	public Location calculateNextLocation(Location destination, int weight) {

		int rowWeight = weight;
		int colWeight = weight;

		if (row == destination.row) {
			rowWeight = 0;
		}
		if (col == destination.col) {
			colWeight = 0;
		}
		if (row > destination.row) {
			rowWeight = -1 * rowWeight;
		}
		if (col > destination.col) {
			colWeight = -1 * colWeight;
		}

		return new Location(row + rowWeight, (char)(col + colWeight));
	}

	public boolean isVertical(Location destination) {
		return col == destination.col;
	}

}

