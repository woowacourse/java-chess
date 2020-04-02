package chess.domain.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import chess.domain.Color;

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
			throw new IllegalArgumentException("체스판 범위를 벗어난 Position을 입력했습니다.");
		}
	}

	public boolean isInitialPawnPosition(Color color) {
		if (color == Color.WHITE) {
			return PositionCache.WHITE_PAWN_INITIAL_POSITION.contains(this);
		}

		if (color == Color.BLACK) {
			return PositionCache.BLACK_PAWN_INITIAL_POSITION.contains(this);
		}
		return false;
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
		private static final List<Position> WHITE_PAWN_INITIAL_POSITION = new ArrayList<>();
		private static final List<Position> BLACK_PAWN_INITIAL_POSITION = new ArrayList<>();

		static {
			makeBoard();
			makeWhitePawnInitialPosition();
			makeBlackPawnInitialPosition();
		}

		private PositionCache() {
		}

		private static void makeWhitePawnInitialPosition() {
			for (File file : File.values()) {
				WHITE_PAWN_INITIAL_POSITION.add(Position.of(file, Rank.TWO));
			}
		}

		private static void makeBlackPawnInitialPosition() {
			for (File file : File.values()) {
				BLACK_PAWN_INITIAL_POSITION.add(Position.of(file, Rank.SEVEN));
			}
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
