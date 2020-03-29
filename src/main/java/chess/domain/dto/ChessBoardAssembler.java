package chess.domain.dto;

import chess.domain.ChessBoard;
import chess.domain.Side;
import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import chess.domain.state.State;

import java.util.ArrayList;
import java.util.List;

public class ChessBoardAssembler {
	private static final int INDEX_ADJUST = 1;
	private static final String BLANK_CHAR = ".";

	private ChessBoardAssembler() {
	}

	public static ChessBoardDto create(State state) {
		int colCount = Column.size();
		int rowCount = Row.size();
		List<List<String>> board = createEmptyBoard(colCount, rowCount);

		ChessBoard chessBoard = state.getChessBoard();
		for (Piece piece : chessBoard.getPieces()) {
			Position position = piece.getPosition();
			Row row = position.getRow();
			Column col = position.getCol();

			List<String> boardRow = board.get(rowCount - row.getSymbol());
			boardRow.set(col.getValue() - INDEX_ADJUST, convertName(piece));
		}
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
			emptyRow.add(BLANK_CHAR);
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
