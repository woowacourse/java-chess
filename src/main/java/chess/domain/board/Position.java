package chess.domain.board;

import chess.domain.piece.Team;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Position {

	private static final String OVER_RANGE_ERROR = "체스판 범위를 벗어나는 입력입니다.";

	private final int row;
	private final int column;

	private static final Map<Integer, Map<Integer, Position>> cachedPositions = new HashMap<>();

	private Position(final int row, final int column) {
		this.row = row;
		this.column = column;
	}

	public static Position of(final int row, final int column) {
		validateRange(row, column);
		cachedPositions.putIfAbsent(row, new HashMap<>());
		cachedPositions.get(row).putIfAbsent(column, new Position(row, column));
		return cachedPositions.get(row).get(column);
	}

	private static void validateRange(final int row, final int column) {
		if (isOverRange(row, column)) {
			throw new IllegalArgumentException(OVER_RANGE_ERROR);
		}
	}

	private static boolean isOverRange(final int row, final int column) {
		return row < 1 || row > 8 || column < 1 || column > 8;
	}

	public static List<Position> getPositions() {
		List<Position> positions = new ArrayList<>();
		for (int i = 1; i < 9; i++) {
			positions.addAll(createRowPositions(i));
		}
		return positions;
	}

	public Optional<Position> addDirection(Direction direction) {
		int row = direction.addRow(this.row);
		int column = direction.addColumn(this.column);
		if (isOverRange(row, column)) {
			return Optional.empty();
		}
		return Optional.of(Position.of(row, column));
	}

	public boolean isDifferentRow(Position position) {
		return this.row != position.row;
	}

	public boolean isDifferentColumn(Position position) {
		return this.column != position.column;
	}

	public boolean isDifferentDiagonal(Position position) {
		return Math.abs(this.row - position.row) != Math.abs(this.column - position.column);
	}

	public int subtractRow(Position position) {
		return Integer.compare(this.row, position.row);
	}

	public int subtractColumn(Position position) {
		return Integer.compare(this.column, position.column);
	}

	private static List<Position> createRowPositions(final int row) {
		List<Position> rowPositions = new ArrayList<>();
		for (int j = 1; j < 9; j++) {
			rowPositions.add(Position.of(row, j));
		}
		return rowPositions;
	}

	public static List<Position> getReversePositions() {
		List<Position> positions = new ArrayList<>();
		for (int i = 8; i >= 1; i--) {
			positions.addAll(createRowPositions(i));
		}
		return positions;
	}

	public boolean isEndColumn() {
		return column == 8;
	}

	public boolean isDefaultRow(final Team team) {
		if (team.isBlack()) {
			return this.row == 7;
		}
		return this.row == 2;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final Position position = (Position) o;
		return row == position.row && column == position.column;
	}

	@Override
	public int hashCode() {
		return Objects.hash(row, column);
	}

	@Override
	public String toString() {
		return "Position{" +
				"row=" + row +
				", column=" + column +
				'}';
	}
}
