package chess.domain.board;

import java.util.HashMap;
import java.util.Map;

import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

public class Board {
	private final Map<Position, String> board = new HashMap<>();

	public void initBoard() {
		initWhitePieces();
		initBlackPieces();
	}

	private void initBlackPieces() {
		board.put(new Position(Row.A, Column.EIGHT), "R");
		board.put(new Position(Row.B, Column.EIGHT), "N");
		board.put(new Position(Row.C, Column.EIGHT), "B");
		board.put(new Position(Row.D, Column.EIGHT), "G");
		board.put(new Position(Row.E, Column.EIGHT), "K");
		board.put(new Position(Row.F, Column.EIGHT), "B");
		board.put(new Position(Row.G, Column.EIGHT), "N");
		board.put(new Position(Row.H, Column.EIGHT), "R");

		initOneLine(Column.SEVEN, "P");
	}

	private void initWhitePieces() {
		board.put(new Position(Row.A, Column.ONE), "r");
		board.put(new Position(Row.B, Column.ONE), "n");
		board.put(new Position(Row.C, Column.ONE), "b");
		board.put(new Position(Row.D, Column.ONE), "g");
		board.put(new Position(Row.E, Column.ONE), "k");
		board.put(new Position(Row.F, Column.ONE), "b");
		board.put(new Position(Row.G, Column.ONE), "n");
		board.put(new Position(Row.H, Column.ONE), "r");

		initOneLine(Column.TWO, "p");
	}

	private void initOneLine(Column column, String string) {
		for (Row row : Row.values()) {
			board.put(new Position(row, column), string);
		}
	}

	public Map<Position, String> getBoard() {
		return board;
	}
}
