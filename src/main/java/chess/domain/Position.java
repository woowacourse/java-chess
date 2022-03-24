package chess.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

	public int subtractRow(Position position) {
		return this.row - position.row;
	}

	public boolean canReach(Position to, UnitPosition unit, int threshold) {
		List<Position> positions = new ArrayList<>();
		for (int i = 1; i <= threshold; i++) {
			positions.add(this.convert(unit.multiply(i)));
		}
		return positions.stream()
			.anyMatch(each -> each.equals(to));
	}

	public Position convert(UnitPosition unitPosition) {
		return new Position(this.row + unitPosition.getUnitRow(), this.column + unitPosition.getUnitColumn());
	}

	public int subtractColumn(Position position) {
		return this.column - position.column;
	}

	public boolean isSameRow(int row) {
		return this.row == row;
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
