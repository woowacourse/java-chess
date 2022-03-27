package chess.domain.board.coordinate;

import java.util.HashMap;
import java.util.Map;

import chess.domain.direction.Direction;
import chess.domain.piece.Team;

public class Coordinate {

	private static final Map<String, Coordinate> CACHE = new HashMap<>();

	static {
		for (Column column : Column.values()) {
			for (Row row : Row.values()) {
				CACHE.put(column.getName() + row.getValue(), new Coordinate(column, row));
			}
		}
	}

	private final Column column;
	private final Row row;

	private Coordinate(Column column, Row row) {
		this.column = column;
		this.row = row;
	}

	public static Coordinate of(String value) {
		return CACHE.get(value);
	}

	public static Coordinate of(Column column, Row row) {
		String key = column.getName() + row.getValue();
		return of(key);
	}

	public Coordinate next(Direction direction) {
		return of(column.move(direction.getColumnVector()), row.move(direction.getRowVector()));
	}

	public boolean isPawnStartRow(Team team) {
		if (team.isSame(Team.BLACK)) {
			return row.getValue() == 7;
		}
		return row.getValue() == 2;
	}

	public Column getColumn() {
		return column;
	}

	public Row getRow() {
		return row;
	}
}
