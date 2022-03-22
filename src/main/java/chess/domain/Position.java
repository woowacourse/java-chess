package chess.domain;

import java.util.Objects;
import java.util.regex.Pattern;

public class Position {

	private static final String ROW_PATTERN = "^[1-8]*$";
	private static final String COLUMN_PATTERN = "^[a-h]*$";
	private static final Pattern COMPILED_ROW_PATTERN = Pattern.compile(ROW_PATTERN);
	private static final Pattern COMPILED_COLUMN_PATTERN = Pattern.compile(COLUMN_PATTERN);
	private static final String INVALID_POSITION_ERROR = "올바르지 않은 위치입니다";

	private final String row;
	private final String column;

	public Position(String row, String column) {
		validate(row, column);
		this.row = row;
		this.column = column;
	}

	private void validate(final String row, final String column) {
		validateRow(row);
		validateColumn(column);
	}

	private void validateRow(String row) {
		if (!COMPILED_ROW_PATTERN.matcher(row).matches()) {
			throw new IllegalArgumentException(INVALID_POSITION_ERROR);
		}
	}

	private void validateColumn(String column) {
		if (!COMPILED_COLUMN_PATTERN.matcher(column).matches()) {
			throw new IllegalArgumentException(INVALID_POSITION_ERROR);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof Position)) {
			return false;
		}
		Position position = (Position)o;
		return Objects.equals(row, position.row) && Objects.equals(column, position.column);
	}

	@Override
	public int hashCode() {
		return Objects.hash(row, column);
	}
}
