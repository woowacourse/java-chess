package chess.domain.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import chess.domain.Color;

public class Position {
	public static final int MIN = 1;
	public static final int MAX = 8;

	private final Column column;
	private final Row row;

	private Position(final Column column, final Row row) {
		this.column = column;
		this.row = row;
	}

	public static Position of(String position) {
		validateInvalidPosition(position);
		return PositionCache.BOARD.get(position);
	}

	public static Position of(int afterMoveOfX, int afterMoveOfY) {
		String afterMovePosition = PositionCache.createKey(Column.of(afterMoveOfX),
			Row.of(afterMoveOfY));
		return Position.of(afterMovePosition);
	}

	public static Position of(Column column, Row row) {
		String position = PositionCache.createKey(column, row);
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

	public int getRow() {
		return row.getRow();
	}

	public int getColumn() {
		return column.getNumber();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Position position = (Position)o;
		return column == position.column &&
			row == position.row;
	}

	@Override
	public int hashCode() {
		return Objects.hash(column, row);
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
			for (Column column : chess.domain.board.Column.values()) {
				WHITE_PAWN_INITIAL_POSITION.add(Position.of(column, Row.TWO));
			}
		}

		private static void makeBlackPawnInitialPosition() {
			for (Column column : chess.domain.board.Column.values()) {
				BLACK_PAWN_INITIAL_POSITION.add(Position.of(column, Row.SEVEN));
			}
		}

		private static void makeBoard() {
			for (Column column : chess.domain.board.Column.values()) {
				makeBoardBy(column);
			}
		}

		private static void makeBoardBy(Column column) {
			for (Row row : Row.values()) {
				BOARD.put(createKey(column, row), new Position(column, row));
			}
		}

		private static String createKey(Column column, Row row) {
			return column.getColumn() + row.getRow();
		}
	}

}
