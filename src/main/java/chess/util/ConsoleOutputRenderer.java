package chess.util;

import static chess.domain.position.Position.*;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import chess.domain.board.Board;
import chess.domain.position.Position;

public class ConsoleOutputRenderer {
	private ConsoleOutputRenderer() {
	}

	public static String renderBoard(Board board) {
		return IntStream.rangeClosed(MIN_POSITION_INDEX, MAX_POSITION_INDEX)
			.mapToObj(col -> renderBoardColumn(board, col))
			.collect(Collectors.joining("\n"));
	}

	private static String renderBoardColumn(Board board, int col) {
		return IntStream.rangeClosed(MIN_POSITION_INDEX, MAX_POSITION_INDEX)
			.mapToObj(row -> Position.of(row, col))
			.map(position -> renderPosition(board, position))
			.collect(Collectors.joining());
	}

	private static String renderPosition(Board board, Position position) {
		if (board.isNotEmptyPosition(position)) {
			return board.findPieceBy(position).getName();
		}
		return ".";
	}
}
