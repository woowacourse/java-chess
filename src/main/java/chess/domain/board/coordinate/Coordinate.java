package chess.domain.board.coordinate;

import java.util.HashMap;
import java.util.Map;

import chess.domain.direction.Direction;
import chess.domain.piece.Team;

public class Coordinate {

    private static final Map<String, Coordinate> CACHE = new HashMap<>();
    private static final int BLACK_PAWN_START_ROW = 7;
    private static final int WHITE_PAWN_START_ROW = 2;

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
        if (!CACHE.containsKey(key)) {
            CACHE.put(key, new Coordinate(column, row));
        }
        return of(key);
	}

	public Coordinate next(Direction direction) {
		return of(column.move(direction.getColumnVector()), row.move(direction.getRowVector()));
	}

	public boolean isPawnStartRow(Team team) {
		if (team.isSame(Team.BLACK)) {
			return row.getValue() == BLACK_PAWN_START_ROW;
		}
		return row.getValue() == WHITE_PAWN_START_ROW;
	}

	public Column getColumn() {
		return column;
	}

	public Row getRow() {
		return row;
	}
}
