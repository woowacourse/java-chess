package chess.domain.board;

import chess.domain.piece.Team;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Position {

	public static final int MIN_POSITION = 1;
	public static final int MAX_POSITION = 8;

	private static final String OVER_RANGE_ERROR = "체스판 범위를 벗어나는 입력입니다.";

	private final int row;
	private final int column;

	private static final Map<Integer, Map<Integer, Position>> cachedPositions = new HashMap<>();

	private Position(final int row, final int column) {
		validateRange(row, column);
		this.row = row;
		this.column = column;
	}

	public static Position of(final int row, final int column) {
		cachedPositions.putIfAbsent(row, new HashMap<>());
		cachedPositions.get(row).putIfAbsent(column, new Position(row, column));
		return cachedPositions.get(row).get(column);
	}

	private void validateRange(final int row, final int column) {
		if (isOverRange(row, column)) {
			throw new IllegalArgumentException(OVER_RANGE_ERROR);
		}
	}

	private boolean isOverRange(final int row, final int column) {
		return row < MIN_POSITION || row > MAX_POSITION || column < MIN_POSITION || column > MAX_POSITION;
	}

	public Position addDirection(Direction direction) {
		int row = direction.addRow(this.row);
		int column = direction.addColumn(this.column);
		return Position.of(row, column);
	}

	public boolean isLinerMove(Position position) {
		return this.row == position.row || this.column == position.column;
	}

	public boolean isDiagonalMove(Position position) {
		return Math.abs(this.row - position.row) == Math.abs(this.column - position.column);
	}

	public List<Position> getArrivalPositionsByDirections(List<Direction> directions) {
		return directions.stream()
				.filter(this::canMoveToDirection)
				.map(this::addDirection)
				.collect(Collectors.toList());
	}

	private boolean canMoveToDirection(Direction direction) {
		int row = direction.addRow(this.row);
		int column = direction.addColumn(this.column);
		return !isOverRange(row, column);
	}

	public int calculateRowDifference(Position position) {
		return Integer.compare(this.row, position.row);
	}

	public int calculateColumnDifference(Position position) {
		return Integer.compare(this.column, position.column);
	}

	public int subtractRow(Position position) {
		return this.row - position.row;
	}

	public int subtractColumn(Position position) {
		return this.column - position.column;
	}

	public boolean isEndColumn() {
		return column == MAX_POSITION;
	}

	public boolean isInitialPawnRow(final Team team) {
		if (team.isBlack()) {
			return this.row == InitialBoard.INITIAL_BLACK_PAWN_ROW;
		}
		return this.row == InitialBoard.INITIAL_WHITE_PAWN_ROW;
	}

	public static List<Position> getPositions() {
		List<Position> positions = new ArrayList<>();
		for (int i = MIN_POSITION; i <= MAX_POSITION; i++) {
			positions.addAll(createRowPositions(i));
		}
		return positions;
	}

	private static List<Position> createRowPositions(final int row) {
		List<Position> rowPositions = new ArrayList<>();
		for (int j = MIN_POSITION; j <= MAX_POSITION; j++) {
			rowPositions.add(Position.of(row, j));
		}
		return rowPositions;
	}

	public static List<Position> getReversePositions() {
		List<Position> positions = new ArrayList<>();
		for (int i = MAX_POSITION; i >= MIN_POSITION; i--) {
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

	@Override
	public String toString() {
		return "Position{" +
				"row=" + row +
				", column=" + column +
				'}';
	}
}
