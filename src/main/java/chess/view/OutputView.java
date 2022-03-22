package chess.view;

import java.util.Map;

import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

public class OutputView {

	private OutputView() {
	}

	public static void printBoard(Map<Position, String> board) {
		for (Column column : Column.reverseColumns()) {
			printColumnWithRow(board, column);
			System.out.println();
		}
	}

	private static void printColumnWithRow(Map<Position, String> board, Column column) {
		for (Row row : Row.values()) {
			Position position = new Position(row, column);
			String piece = board.get(position);
			printStringOrDefault(piece);
		}
	}

	private static void printStringOrDefault(String piece) {
		if (piece != null) {
			System.out.print(piece);
			return;
		}
		System.out.print(".");
	}
}
