package chess.domain.piece;

import static chess.domain.piece.Direction.*;

import java.util.List;

import chess.domain.board.Position;
import chess.domain.board.Row;

public class Pawn extends Piece {

	private static final List<Direction> WHITE_POSSIBLE_DIRECTIONS = List.of(N, NE, NW);
	private static final List<Direction> BLACK_POSSIBLE_DIRECTIONS = List.of(S, SE, SW);
	private static final int POSSIBLE_DISTANCE = 1;
	private static final int POSSIBLE_INITIAL_DISTANCE = 2;
	private static final Row WHITE_INITIAL_ROW = Row.SECOND;
	private static final Row BLACK_INITIAL_ROW = Row.SEVENTH;

	public Pawn(final Color color) {
		super("pawn", color);
	}

	@Override
	public Direction findValidDirection(Position current, Position target) {
		int rowDifference = target.calculateRowDifference(current);
		int columnDifference = target.calculateColumnDifference(current);
		Direction direction = Direction.calculate(rowDifference, columnDifference);
		validateDirection(direction);
		if (isFirstMove(current.getRow())) {
			validateInitialRange(rowDifference, columnDifference);
			return direction;
		}
		validateRange(rowDifference, columnDifference);
		return direction;
	}

	private void validateInitialRange(int rowDifference, int columnDifference) {
		if (isValidInitialRange(rowDifference, columnDifference)) {
			throw new IllegalArgumentException("진행할 수 없는 위치입니다.");
		}
	}

	private boolean isValidInitialRange(int rowDifference, int columnDifference) {
		return Math.abs(rowDifference) > POSSIBLE_INITIAL_DISTANCE || Math.abs(columnDifference) > POSSIBLE_DISTANCE;
	}

	private void validateDirection(Direction direction) {
		if (isInvalidDirection(direction)) {
			throw new IllegalArgumentException("진행할 수 없는 방향입니다.");
		}
	}

	private boolean isInvalidDirection(final Direction direction) {
		if (getColor() == Color.BLACK) {
			return isInvalidBlackDirection(direction);
		}
		return isInvalidWhiteDirection(direction);
	}

	private boolean isInvalidBlackDirection(Direction direction) {
		return !BLACK_POSSIBLE_DIRECTIONS.contains(direction);
	}

	private boolean isInvalidWhiteDirection(Direction direction) {
		return !WHITE_POSSIBLE_DIRECTIONS.contains(direction);
	}

	private boolean isFirstMove(final Row row) {
		return (row == WHITE_INITIAL_ROW && getColor() == Color.WHITE)
			|| (row == BLACK_INITIAL_ROW && getColor() == Color.BLACK);
	}

	private void validateRange(int rowDifference, int columnDifference) {
		if (isInvalidRange(rowDifference, columnDifference)) {
			throw new IllegalArgumentException("진행할 수 없는 위치입니다.");
		}
	}

	private boolean isInvalidRange(int rowDifference, int columnDifference) {
		return Math.abs(rowDifference) > POSSIBLE_DISTANCE || Math.abs(columnDifference) > POSSIBLE_DISTANCE;
	}
}
