package chess.domain.position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import chess.domain.piece.Team;

public class Position {
	public static final int MINIMUM_POSITION_NUMBER = 1;
	public static final int MAXIMUM_POSITION_NUMBER = 8;
	private static final int KNIGHT_MULTIPLICATION_OF_BETWEEN_FILE_DISTANCE_AND_RANK_DISTANCE = 2;
	private static final Map<String, Position> CACHE = new HashMap<>();

	static {
		initPositionCache();
	}

	private static void initPositionCache() {
		for (int col = MINIMUM_POSITION_NUMBER; col <= MAXIMUM_POSITION_NUMBER; col++) {
			for (int row = MINIMUM_POSITION_NUMBER; row <= MAXIMUM_POSITION_NUMBER; row++) {
				CACHE.put(getKey(col, row), new Position(new Cell(col), new Cell(row)));
			}
		}
	}

	private final Cell col;
	private final Cell row;

	private Position(Cell file, Cell rank) {
		this.col = Objects.requireNonNull(file);
		this.row = Objects.requireNonNull(rank);
	}

	public static Position of(String key) {
		return CACHE.get(key);
	}

	public static Position of(int file, int rank) {
		return CACHE.get(getKey(file, rank));
	}

	private static Position of(Cell file, Cell rank) {
		return CACHE.get(getKey(file.getNumber(), rank.getNumber()));
	}

	private static String getKey(int file, int rank) {
		return (char)('a' + file - 1) + String.valueOf(rank);
	}

	public static List<Position> findMultipleStepTrace(Position from, Position to) {
		if (from.isSameRow(to)) {
			List<Cell> files = Cell.valuesBetween(from.getFile(), to.getFile());
			return files.stream()
				.map(file -> Position.of(file, from.getRank()))
				.collect(Collectors.toList());
		}
		if (from.isDiagonal(to)) {
			List<Position> positions = new ArrayList<>();
			List<Cell> files = Cell.valuesBetween(from.getFile(), to.getFile());
			List<Cell> ranks = Cell.valuesBetween(from.getRank(), to.getRank());
			for (int i = 0; i < files.size(); i++) {
				positions.add(Position.of(files.get(i), ranks.get(i)));
			}
			return positions;
		}
		if (from.isSameColumn(to)) {
			List<Cell> ranks = Cell.valuesBetween(from.getRank(), to.getRank());
			return ranks.stream()
				.map(rank -> Position.of(from.getFile(), rank))
				.collect(Collectors.toList());
		}
		throw new IllegalArgumentException("해당 위치로 이동할 수 없습니다.");
	}

	public boolean isInitialPawnPosition(Team team) {
		return row.isInitialPawnRow(team);
	}

	public boolean isNotDiagonal(Position other) {
		return !isDiagonal(other);
	}

	public boolean isDiagonal(Position other) {
		return col.calculateAbsolute(other.col) == row.calculateAbsolute(other.row);
	}

	public boolean isNotStraght(Position other) {
		return !col.equals(other.col) && !row.equals(other.row);
	}

	public boolean isSameRow(Position other) {
		return row.equals(other.row);
	}

	public boolean isSameColumn(Position other) {
		return col.equals(other.col);
	}

	public boolean isNotDistanceOneSquare(Position other) {
		return !(this.row.isNear(other.row) && this.col.isNear(other.col));
	}

	public boolean isNotKnightMovable(Position other) {
		return this.col.calculateAbsolute(other.col) * this.row.calculateAbsolute(other.row)
			!= KNIGHT_MULTIPLICATION_OF_BETWEEN_FILE_DISTANCE_AND_RANK_DISTANCE;
	}

	public Cell getFile() {
		return col;
	}

	public Cell getRank() {
		return row;
	}

	public int getRankNumber() {
		return row.getNumber();
	}

	@Override
	public String toString() {
		return String.valueOf((char)('a' + col.getNumber() - 1)) + row.getNumber();
	}
}
