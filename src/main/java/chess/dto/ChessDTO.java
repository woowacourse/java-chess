package chess.dto;

import java.util.List;
import java.util.stream.IntStream;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Row;

public class ChessDTO {
	private static final int FROM_INDEX = 0;
	private static final int TO_INDEX = 8;

	private final int id;
	private final String rawBoard;
	private final boolean isWhiteTurn;

	public ChessDTO(int id, String rawBoard, boolean isWhiteTurn) {
		this.id = id;
		this.rawBoard = rawBoard;
		this.isWhiteTurn = isWhiteTurn;
	}

	public ChessDTO(ChessBoard chessBoard) {
		this.rawBoard = reverseToString(chessBoard);
		this.id = chessBoard.getId();
		this.isWhiteTurn = chessBoard.isWhiteTurn();
	}

	private String reverseToString(ChessBoard chessBoard) {
		StringBuilder builder = new StringBuilder();
		List<Row> rows = chessBoard.getRows();
		int size = rows.size();
		for (int index = size - 1; index >= 0; index--) {
			Row row = rows.get(index);
			IntStream.range(FROM_INDEX, TO_INDEX)
				.mapToObj(num -> row.get(num).getName())
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
