package chess.domain.board;

import java.util.HashMap;
import java.util.Map;

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

	public int getRank() {
		return rank.getRank();
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
			return file.getFile() + rank.getRank();
		}
	}

}
