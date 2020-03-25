package chess.domain.position;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Position {
	private static final Map<String, Position> POSITION_CACHE = generateCache();

	private Column column;
	private Row row;

	private Position(Column column, Row row) {
		this.column = column;
		this.row = row;
	}

	public static Position of(Column columnInput, Row rowInput) {
		validateNull(columnInput, rowInput);
		return POSITION_CACHE.get(columnInput.getName() + rowInput.getName());
	}

	public static Position of(int column, int row) {
		return of(Column.of(column), Row.of(row));
	}

	private static void validateNull(Column columnInput, Row rowInput) {
		if (Objects.isNull(columnInput) || Objects.isNull(rowInput)) {
			throw new IllegalArgumentException("Row 또는 Column이 null입니다.");
		}
	}

	private static Map<String, Position> generateCache() {
		return Column.columnNames().stream()
			.flatMap(column ->
				Row.rowNames().stream()
					.map(row -> new Position(column, row))
			).collect(Collectors.toMap(Position::key, position -> position));
	}

	public boolean canMoveNext(Direction direction) {
		int columnDirection = direction.getColumnDirection();
		int rowDirection = direction.getRowDirection();

		Column nextColumn = column.nextColumn(columnDirection);
		Row nextRow = row.nextRow(rowDirection);

		return nextColumn != null && nextRow != null;
	}

	public Position next(Direction direction) {
		int columnDirection = direction.getColumnDirection();
		int rowDirection = direction.getRowDirection();

		Column nextColumn = column.nextColumn(columnDirection);
		Row nextRow = row.nextRow(rowDirection);

		return Position.of(nextColumn, nextRow);
	}

	public boolean isSameRow(Position that) {
		return column.equals(that.column);
	}

	public boolean isSameColumn(Position that) {
		return row.equals(that.row);
	}

	private String key() {
		return column.getName() + row.getName();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Position position = (Position)o;
		return column == position.column &&
			row == position.row;
	}

	@Override
	public int hashCode() {
		return Objects.hash(column, row);
	}

	@Override
	public String toString() {
		return column.getName() + row.getName();
	}
}
