package chess.board;

import static java.lang.Math.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import chess.piece.stategy.Direction;
import chess.team.Team;

public class Location {
	private static final List<Location> WHITE_TEAM_INITIAL_PAWN_LOCATIONS = Arrays.asList(
		Location.of(Column.A, Row.TWO),
		Location.of(Column.B, Row.TWO),
		Location.of(Column.C, Row.TWO),
		Location.of(Column.D, Row.TWO),
		Location.of(Column.E, Row.TWO),
		Location.of(Column.F, Row.TWO),
		Location.of(Column.G, Row.TWO),
		Location.of(Column.H, Row.TWO)
	);
	private static final List<Location> BLACK_TEAM_INITIAL_PAWN_LOCATIONS = Arrays.asList(
		Location.of(Column.A, Row.SEVEN),
		Location.of(Column.B, Row.SEVEN),
		Location.of(Column.C, Row.SEVEN),
		Location.of(Column.D, Row.SEVEN),
		Location.of(Column.E, Row.SEVEN),
		Location.of(Column.F, Row.SEVEN),
		Location.of(Column.G, Row.SEVEN),
		Location.of(Column.H, Row.SEVEN)
	);

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

	public static Location of(Location now) {
		return of(now.column, now.row);
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
			return BLACK_TEAM_INITIAL_PAWN_LOCATIONS.contains(this);
		}
		return WHITE_TEAM_INITIAL_PAWN_LOCATIONS.contains(this);
	}

	public boolean validEverySpaceRange(Location destination, List<Direction> directions) {
		for (int weight = 1; weight <= 8; weight++) {
			if (validOneSpaceRange(destination, directions, weight)) {
				return true;
			}
		}
		return false;
	}

	public boolean validOneSpaceRange(Location destination, List<Direction> directions, int weight) {
		Location nextLocation = this;
		for (Direction direction : directions) {
			try {
				Column nextColumn = column.add(direction.getXDegree() * weight);
				Row nextRow = row.add(direction.getYDegree() * weight);
				nextLocation = Location.of(nextColumn, nextRow);
			} catch (IllegalArgumentException ignore) {
			}
			if (nextLocation == destination) {
				return true;
			}
		}
		return false;
	}

	public boolean isSameColumn(Location other) {
		return column == other.column;
	}

	public boolean isDiagonal(Location destination) {
		return abs(column.minus(destination.column)) == abs(row.minus(destination.row));
	}

	public boolean isStraight(Location destination) {
		return isSameColumn(destination) || (row == destination.row);
	}

	public boolean isTwoSpaceMove(Location destination) {
		int columnAbs = abs(column.minus(destination.column));
		int rowAbs = abs(row.minus(destination.row));
		return columnAbs == 2 || rowAbs == 2;
	}

	@Override
	public String toString() {
		return "Location{" +
			"column=" + column +
			", row=" + row +
			'}';
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
