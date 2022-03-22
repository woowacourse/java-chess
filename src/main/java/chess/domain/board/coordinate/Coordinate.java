package chess.domain.board.coordinate;

import java.util.HashMap;
import java.util.Map;

public class Coordinate {

	private static final Map<String, Coordinate> CACHE = new HashMap<>();

	static {
		for (Column column : Column.values()) {
			for (Row row : Row.values()) {
				CACHE.put(column.getValue() + row.getValue(), new Coordinate(column, row));
			}
		}
	}

	private final Column column;
	private final Row row;

	private Coordinate(Column column, Row row) {
		this.column = column;
		this.row = row;
	}

	public static Coordinate of(Column column, Row row) {
		String key = column.getValue() + row.getValue();
		return CACHE.get(key);
	}


}
