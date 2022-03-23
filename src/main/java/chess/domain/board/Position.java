package chess.domain.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Position {

	private final int row;
	private final int column;

	private static final Map<Integer, Map<Integer, Position>> cachedPositions = new HashMap<>();

	private Position(final int row, final int column) {
		this.row = row;
		this.column = column;
	}

	public static Position of(final int row, final int column) {
		cachedPositions.putIfAbsent(row, new HashMap<>());
		cachedPositions.get(row).putIfAbsent(column, new Position(row, column));
		return cachedPositions.get(row).get(column);
	}

	public static List<Position> getPositions() {
		List<Position> positions = new ArrayList<>();
		for (int i = 1; i < 9; i++) {
			positions.addAll(createRowPositions(i));
		}
		return positions;
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

	public boolean isEndColumn() {
		return column == 8;
	}
}
