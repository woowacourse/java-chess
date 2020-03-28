package chess.domain.position;

import java.util.Arrays;

public class Position {
	private final Row row;
	private final Column column;

	public Position(Row row, Column column) {
		this.row = row;
		this.column = column;
	}

	public static Position of(String key) {
		return Arrays.stream(Row.values())
			.flatMap(row -> Arrays.stream(Column.values())
				.filter(column -> (row.getName() + column.getName()).equalsIgnoreCase(key))
				.map(column -> new Position(row, column))
			)
			.findFirst()
			.orElseThrow(IllegalArgumentException::new);
	}

	public Row getRow() {
		return row;
	}

	public Column getColumn() {
		return column;
	}
}
