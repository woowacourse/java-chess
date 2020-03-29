package chess.board;

import static java.lang.Math.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

import chess.team.Team;

// 팀별 초기위치를 갖고있는다.
public class Location {
	private static final Location[] WHITE_TEAM_INITIAL_PAWN_LOCATIONS = {
		Location.of(2, 'a'),
		Location.of(2, 'b'),
		Location.of(2, 'c'),
		Location.of(2, 'd'),
		Location.of(2, 'e'),
		Location.of(2, 'f'),
		Location.of(2, 'g'),
		Location.of(2, 'h')
	};
	private static final Location[] BLACK_TEAM_INITIAL_PAWN_LOCATIONS = {
		Location.of(7, 'a'),
		Location.of(7, 'b'),
		Location.of(7, 'c'),
		Location.of(7, 'd'),
		Location.of(7, 'e'),
		Location.of(7, 'f'),
		Location.of(7, 'g'),
		Location.of(7, 'h')
	};
	private final int row;
	private final char col;

	private Location(final int row, final char col) {
		this.row = row;
		this.col = col;
	}

	public static Location of(String input) {
		Location location = LocationCache.cache.get(input);
		Objects.requireNonNull(location, "잘못된 좌표를 입력했습니다.");
		return location;
	}

	public static Location of(int row, char col) {
		return of(String.format("%c%d", col, row));
	}

	public boolean isSameCol(Location other) {
		return this.col == other.col;
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

	public boolean isQueenRange(Location destination) {
		return isDiagonal(destination) || isStraight(destination);
	}

	public boolean isStraight(Location destination) {
		return this.row == destination.row || isVertical(destination);
	}

	public boolean isInitialPawnLocation(Team team) {
		if (team == Team.BLACK) {
			return Arrays.asList(BLACK_TEAM_INITIAL_PAWN_LOCATIONS)
				.contains(this);
		}
		return Arrays.asList(WHITE_TEAM_INITIAL_PAWN_LOCATIONS)
			.contains(this);
	}

	public boolean isInitialPawnForwardRange(Location after, Team team) {
		int weight = 1;
		if (team == Team.BLACK) {
			weight = -1;
		}

		boolean result = row + weight == after.row || row + (weight * 2) == after.row;

		return result && col == after.col;
	}

	public boolean isPawnForwardRange(Location after, Team team) {
		int weight = 1;
		if (team == Team.BLACK) {
			weight = -1;
		}

		boolean result = (row + weight) == after.row;

		return result && col == after.col;
	}

	public boolean isForwardDiagonal(Location after, Team team) {
		int weight = 1;

		if (Team.BLACK == team) {
			weight = -1;
		}

		return this.row + weight == after.row
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

		return Location.of(row + rowWeight, (char)(col + colWeight));
	}

	public boolean isVertical(Location destination) {
		return col == destination.col;
	}

	private static class LocationCache {
		private static final HashMap<String, Location> cache;

		static {
			cache = new HashMap<>();

			for (int row = 1; row <= 8; row++) {
				for (int column = 0; column < 8; column++) {
					char nowColumn = (char)(column + 'a');
					String key = String.format("%c%d", nowColumn, row);
					cache.put(key, new Location(row, nowColumn));
				}
			}
		}
	}
}

