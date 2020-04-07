package chess.dto;

import java.util.List;
import java.util.stream.IntStream;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Row;

public class ChessDTO {
	private static final int FROM_INDEX = 0;
	private static final int TO_INDEX = 7;

	private final int id;
	private final String rawBoard;
	private final boolean isWhiteTurn;

	public ChessDTO(int id, String rawBoard, boolean isWhiteTurn) {
		this.id = id;
		this.rawBoard = rawBoard;
		this.isWhiteTurn = isWhiteTurn;
	}

	public ChessDTO(ChessBoard chessBoard) {
		this.rawBoard = parsing(chessBoard);
		this.id = chessBoard.getId();
		this.isWhiteTurn = chessBoard.isWhiteTurn();
	}

	private String parsing(ChessBoard chessBoard) {
		StringBuilder builder = new StringBuilder();
		List<Row> board = chessBoard.getRows();
		for (Row row : board) {
			IntStream.rangeClosed(FROM_INDEX, TO_INDEX)
				.mapToObj(index -> row.get(index))
				.map(chessPiece -> chessPiece.getName())
				.forEach(name -> builder.append(name));
			builder.append(System.lineSeparator());
		}
		return builder.toString();
	}

	public String getRawBoard() {
		return rawBoard;
	}

	public String[] getSplitRows() {
		return rawBoard.split(System.lineSeparator());
	}

	public boolean isWhiteTurn() {
		return isWhiteTurn;
	}

	public int getId() {
		return id;
	}
}
