package chess.util;

import static chess.domain.coordinates.Coordinates.MAX_POSITION_INDEX;
import static chess.domain.coordinates.Coordinates.MIN_POSITION_INDEX;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import chess.domain.board.Board;
import chess.domain.coordinates.Coordinates;
import chess.domain.piece.Piece;

public class ConsoleOutputRenderer {

	private static final String EMPTY_POSITION = ".";
	private static final int BOARD_SIZE = 8;

	private ConsoleOutputRenderer() {
	}

	public static String renderBoard(Board board) {
		return IntStream.rangeClosed(MIN_POSITION_INDEX, MAX_POSITION_INDEX)
				.mapToObj(index -> renderBoardColumn(board, BOARD_SIZE - index + 1))
				.collect(Collectors.joining("\n"));
	}

	private static String renderBoardColumn(Board board, int col) {
		return IntStream.rangeClosed(MIN_POSITION_INDEX, MAX_POSITION_INDEX)
				.mapToObj(row -> Coordinates.of(row, col))
				.map(position -> renderPosition(board, position))
				.collect(Collectors.joining());
	}

	private static String renderPosition(Board board, Coordinates coordinates) {
		return board.findPieceBy(coordinates)
				.map(Piece::getName)
				.orElse(EMPTY_POSITION);
	}
}
