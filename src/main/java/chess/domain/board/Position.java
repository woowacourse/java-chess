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

	public static Position of(final File file, final Rank rank) {
		return SquareCache.BOARD.get(SquareCache.createKey(file, rank));
	}

	private static class SquareCache {
		private static final Map<String, Position> BOARD = new HashMap<>();

		static {
			makeBoard();
		}

		private SquareCache() {

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
			return file.name() + rank.name();
		}
	}

}
