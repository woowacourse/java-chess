package chess.board;

import static java.lang.Math.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

import chess.team.Team;

public class Location {
	private static final Location[] WHITE_TEAM_INITIAL_PAWN_LOCATIONS = {
		Location.of('a', 2),
		Location.of('b', 2),
		Location.of('c', 2),
		Location.of('d', 2),
		Location.of('e', 2),
		Location.of('f', 2),
		Location.of('g', 2),
		Location.of('h', 2)
	};
	private static final Location[] BLACK_TEAM_INITIAL_PAWN_LOCATIONS = {
		Location.of('a', 7),
		Location.of('b', 7),
		Location.of('c', 7),
		Location.of('d', 7),
		Location.of('e', 7),
		Location.of('f', 7),
		Location.of('g', 7),
		Location.of('h', 7)
	};

	private final Column column;
	private final Row row;

	public Location(Column column, Row row) {
		this.column = column;
		this.row = row;
	}

	public static Location of(String input) {
		Location location = Location.LocationCache.cache.get(input);
		Objects.requireNonNull(location, "잘못된 좌표를 입력했습니다.");
		return location;
	}

	public static Location of(char col, int row) {
		return of(String.format("%c%d", col, row));
	}

	public static Location of(Column column, Row row) {
		return of(String.format("%c%d", column.getSymbol(), row.getNumber()));
	}

	public boolean isSameCol(Location other) {
		return this.column == other.column;
	}

	public boolean isDiagonal(Location destination) {
		return abs(row.minus(destination.row)) == abs(column.minus(destination.column));
	}

	public boolean isStraight(Location destination) {
		return this.row == destination.row || isSameCol(destination);
	}

	public Location calculateNextLocation(Location destination, int weight) {
		int rowWeight = weight;
		int colWeight = weight;

		if (row == destination.row) {
			rowWeight = 0;
		}
		if (column == destination.column) {
			colWeight = 0;
		}
		if (row.isGreaterThan(destination.row)) {
			rowWeight = -1 * rowWeight;
		}
		if (column.isGreaterThan(destination.column)) {
			colWeight = -1 * colWeight;
		}

		return Location.of(column.add(colWeight), row.add(rowWeight));
	}

	public boolean isInitialPawnLocation(Team team) {
		if (Team.BLACK == team) {
			return Arrays.asList(BLACK_TEAM_INITIAL_PAWN_LOCATIONS)
				.contains(this);
		}
		return Arrays.asList(WHITE_TEAM_INITIAL_PAWN_LOCATIONS)
			.contains(this);
	}

	private static class LocationCache {
		private static final HashMap<String, Location> cache;

		static {
			cache = new HashMap<>();

			for (int row = 1; row <= ChessBoard.ROW_LENGTH; row++) {
				for (int column = 0; column < ChessBoard.COLUMN_LENGTH; column++) {
					char nowColumn = (char)(column + 'a');
					String key = String.format("%c%d", nowColumn, row);
					cache.put(key, new Location(Column.of(nowColumn), Row.of(row)));
				}
			}
		}
	}
}
