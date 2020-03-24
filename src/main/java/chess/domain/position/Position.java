package chess.domain.position;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Position {
	private static final Map<String, Position> POSITION_CACHE = generateCache();

	private Row row;
	private Column column;

	private Position(Row row, Column column) {
		this.row = row;
		this.column = column;
	}

	public static Position of(Row rowInput, Column columnInput) {
		validateNull(rowInput, columnInput);
		return POSITION_CACHE.get(rowInput.name() + columnInput.name());
	}

	private static void validateNull(Row rowInput, Column columnInput) {
		if (Objects.isNull(rowInput) || Objects.isNull(columnInput)) {
			throw new IllegalArgumentException("Row 또는 Column이 null입니다.");
		}
	}

	private static Map<String, Position> generateCache() {
		return Arrays.stream(Row.values())
			.flatMap(row1 ->
				Arrays.stream(Column.values())
					.map(column1 -> new Position(row1, column1))
			).collect(Collectors.toMap(Position::key, position -> position));
	}

	public boolean isSameRow(Position that) {
		return row.equals(that.row);
	}

	public boolean isSameColumn(Position that) {
		return column.equals(that.column);
	}

	private String key() {
		return row.name() + column.name();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Position position = (Position)o;
		return row == position.row &&
			column == position.column;
	}

	@Override
	public int hashCode() {
		return Objects.hash(row, column);
	}
}
