package chess.domain;

import java.util.Objects;
import java.util.function.IntUnaryOperator;

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

	public Position change(int row, int column) {
		return new Position(row, column);
	}

	public Position change(IntUnaryOperator rowOperator, IntUnaryOperator columnOperator) {
		try {
			return new Position(rowOperator.applyAsInt(this.row), columnOperator.applyAsInt(this.column));
		} catch (IllegalArgumentException exception) {
			return new Position(this.row, this.column);
		}
	}

	public boolean isDifferentRow(int row) {
		return this.row != row;
	}

	public boolean isDifferentColumn(int column) {
		return this.column != column;
	}

	public boolean isNotAbsoluteSlopeOne(int row, int column) {
		return Math.abs(column - this.column) != Math.abs(row - this.row);
	}

	public boolean isNotAbsoluteDifferenceOne(int row, int column) {
		return Math.abs(column - this.column) > 1 || Math.abs(row - this.row) > 1;
	}

	public boolean isNotAbsoluteSlopeTwoOrHalf(int row, int column) {
		return !((Math.abs(this.row - row) == 1 && Math.abs(this.column - column) == 2) ||
			(Math.abs(this.row - row) == 2 && Math.abs(this.column - column) == 1));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Position position = (Position)o;
		return row == position.row && column == position.column;
	}

	@Override
	public int hashCode() {
		return Objects.hash(row, column);
	}
}
