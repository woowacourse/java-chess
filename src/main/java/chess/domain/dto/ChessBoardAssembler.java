package chess.domain.dto;

import chess.domain.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Row;

import java.util.ArrayList;
import java.util.List;

public class ChessBoardAssembler {
	private static final int INDEX_ADJUST = 1;
	private static final int ROW_COUNT = Row.size();
	private static final int COL_COUNT = Column.size();
	private static final String BLANK_CHAR = "";

	private ChessBoardAssembler() {
	}

	public static ChessBoardDto create(ChessBoard chessBoard) {
		List<List<String>> board = createEmptyBoard();

		for (Piece piece : chessBoard.getPieces()) {
			PieceDto pieceDto = PieceAssembler.createDto(piece);

			List<String> boardRow = board.get(ROW_COUNT - pieceDto.getRow());
			boardRow.set(pieceDto.getCol() - INDEX_ADJUST, pieceDto.getName());
		}
		return new ChessBoardDto(board);
	}

	private static List<List<String>> createEmptyBoard() {
		List<List<String>> board = new ArrayList<>();
		for (int i = 0; i < ROW_COUNT; i++) {
			createColumn(board);
		}
		return board;
	}

	private static void createColumn(List<List<String>> board) {
		List<String> emptyRow = new ArrayList<>();
		for (int j = 0; j < COL_COUNT; j++) {
			emptyRow.add(BLANK_CHAR);
		}
		board.add(emptyRow);
	}
}
