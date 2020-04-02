package chess.domain.coordinates;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Coordinates {
	public static final int MIN_POSITION_INDEX = 1;
	public static final int MAX_POSITION_INDEX = 8;
	private static final String DELIMITER = "";
	private static final int COLUMN_INPUT_INDEX = 0;
	private static final int ROW_INPUT_INDEX = 1;

	private static final Map<String, Coordinates> POSITION_CACHE = generatePositions();

	private final Column column;
	private final Row row;

	private Coordinates(Column column, Row row) {
		this.column = column;
		this.row = row;
	}

	public static Coordinates of(String input) {
		String[] coordinates = input.split(DELIMITER);
		if (coordinates.length != 2) {
			throw new IllegalArgumentException("존재하지 않는 좌표입니다.");
		}
		return Coordinates.of(Column.of(coordinates[COLUMN_INPUT_INDEX]), Row.of(coordinates[ROW_INPUT_INDEX]));
	}

	public static Coordinates of(Column columnInput, Row rowInput) {
		validateNull(columnInput, rowInput);
		return POSITION_CACHE.get(columnInput.getName() + rowInput.getName());
	}

	public static Coordinates of(int column, int row) {
		return of(Column.of(column), Row.of(row));
	}

	private static void validateNull(Column columnInput, Row rowInput) {
		if (Objects.isNull(columnInput) || Objects.isNull(rowInput)) {
			throw new IllegalArgumentException("Row 또는 Column이 null입니다.");
		}
	}

	private static Map<String, Coordinates> generatePositions() {
		return Column.values()
				.stream()
				.flatMap(Coordinates::generatePositionsByColumn)
				.collect(Collectors.toMap(Coordinates::getName, position -> position));
	}

	private static Stream<Coordinates> generatePositionsByColumn(Column column) {
		return Row.values()
				.stream()
				.map(row -> new Coordinates(column, row));
	}

	public int calculateRowGap(Coordinates that) {
		return this.row.calculateGap(that.row);
	}

	public int calculateColumnGap(Coordinates that) {
		return this.column.calculateGap(that.column);
	}

	public Coordinates next(Direction direction) {
		return Coordinates.of(column.next(direction.getColumnOffset()), row.next(direction.getRowOffset()));
	}

	public String getName() {
		return column.getName() + row.getName();
	}

	public Column getColumn() {
		return column;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Coordinates coordinates = (Coordinates)o;
		return column == coordinates.column &&
				row == coordinates.row;
	}

	@Override
	public int hashCode() {
		return Objects.hash(column, row);
	}
}
