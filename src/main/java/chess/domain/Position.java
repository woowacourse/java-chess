package chess.domain;

public class Position {

	private static final String INVALID_RANGE_ERROR = "유효한 범위가 아닙니다.";
	private static final int MIN = 1;
	private static final int MAX = 8;

	private final int row;
	private final int column;

	public Position(int row, int column) {
		validatePosition(row, column);
		this.row = row;
		this.column = column;
	}

	private void validatePosition(int row, int column) {
		if (row < MIN || row > MAX || column < MIN || column > MAX) {
			throw new IllegalArgumentException(INVALID_RANGE_ERROR);
		}
	}

	public boolean isSame(int row, int column) {
		return this.row == row && this.column == column;
	}
}
