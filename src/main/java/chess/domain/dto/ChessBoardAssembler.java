package chess.domain.dto;

import chess.domain.Side;
import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Row;
import chess.domain.state.State;

import java.util.ArrayList;
import java.util.List;

public class ChessBoardAssembler {
	private static final int INDEX_ADJUST = 1;

	private ChessBoardAssembler() {
	}

	public static ChessBoardDto create(State state) {
		int col = Column.values().length;
		int row = Row.values().length;

		List<List<String>> board = createEmptyBoard(col, row);
		state.getChessBoard().getPieces()
				.forEach(piece -> board.get(row - piece.getPosition().getRow().getSymbol())
						.set(piece.getPosition().getCol().getValue() - INDEX_ADJUST,
								convertName(piece)));
		return new ChessBoardDto(board);
	}

	private static List<List<String>> createEmptyBoard(int col, int row) {
		List<List<String>> board = new ArrayList<>();
		for (int i = 0; i < row; i++) {
			createColumn(board, col);
		}
		return board;
	}

	private static void createColumn(List<List<String>> board, int col) {
		List<String> emptyRow = new ArrayList<>();
		for (int j = 0; j < col; j++) {
			emptyRow.add(".");
		}
		board.add(emptyRow);
	}

	private static String convertName(Piece piece) {
		if (piece.getSide() == Side.BLACK) {
			return piece.getName().toUpperCase();
		}
		return piece.getName();
	}
}
