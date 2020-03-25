package chess.util;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import chess.domain.Board;
import chess.domain.position.Position;

public class ConsoleOutputRenderer {
	private ConsoleOutputRenderer() {
	}

	public static String renderBoard(Board board) {
		return IntStream.rangeClosed(1, 8)
			.mapToObj(col -> renderBoardColumn(board, col))
			.collect(Collectors.joining("\n"));
	}

	private static String renderBoardColumn(Board board, int col) {
		return IntStream.rangeClosed(1, 8)
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
