package chess.domain.board;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

	public static Position of(int afterMoveOfX, int afterMoveOfY) {
		String afterMovePosition = PositionCache.createKey(File.of(afterMoveOfX), Rank.of(afterMoveOfY));
		return Position.of(afterMovePosition);
	}

	public static Position of(File file, Rank rank) {
		String position = PositionCache.createKey(file, rank);
		return Position.of(position);
	}

	private static void validateInvalidPosition(String position) {
		if (!PositionCache.BOARD.containsKey(position)) {
			throw new IllegalArgumentException("유효하지 않은 Position을 입력했습니다.");
		}
	}

	public int getRank() {
		return rank.getRank();
	}

	public int getColumn() {
		return file.getColumn();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Position position = (Position)o;
		return file == position.file &&
			rank == position.rank;
	}

	@Override
	public int hashCode() {
		return Objects.hash(file, rank);
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
			return file.getFile() + rank.getRank();
		}
	}

}
