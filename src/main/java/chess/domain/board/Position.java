package chess.domain.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import chess.domain.piece.Direction;

public class Position {
	private final File file;
	private final Rank rank;

	private Position(final File file, final Rank rank) {
		this.file = file;
		this.rank = rank;
	}

	public static Position of(String position) {
		validateInvalidPosition(position);
		return PositionCache.BOARD.get(position);
	}

	private static void validateInvalidPosition(String position) {
		if (!PositionCache.BOARD.containsKey(position)) {
			throw new IllegalArgumentException("유효하지 않은 Position을 입력했습니다.");
		}
	}

	public List<Position> nextPosition(List<Direction> directions) {
		List<Position> nextPositions = new ArrayList<>();

		for (Direction direction : directions) {
			try {
				Rank nextRank = Rank.of(getRank() + direction.getX());
				File nextFile = File.of(getColumn() + direction.getY());

				nextPositions.add(Position.of(nextFile.getFile() + nextRank.getRow()));
			} catch (IllegalArgumentException ignored) {
			}
		}
		return nextPositions;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Position))
			return false;
		Position position = (Position)o;
		return file == position.file &&
			rank == position.rank;
	}

	@Override
	public int hashCode() {
		return Objects.hash(file, rank);
	}

	public int getRank() {
		return rank.getRow();
	}

	public int getColumn() {
		return file.getColumn();
	}

	private static class PositionCache {
		private static final Map<String, Position> BOARD = new HashMap<>();

		static {
			makeBoard();
		}

		private PositionCache() {
		}

		private static void makeBoard() {
			for (File file : File.values()) {
				makeBoardBy(file);
			}
		}

		private static void makeBoardBy(File file) {
			for (Rank rank : Rank.values()) {
				BOARD.put(createKey(file, rank), new Position(file, rank));
			}
		}

		private static String createKey(File file, Rank rank) {
			return file.getFile() + rank.getRow();
		}
	}

}
