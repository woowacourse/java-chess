package chess.web;

import java.util.Map;

public class ChessBoardDto {

	private final Map<String, String> chessBoard;

	public ChessBoardDto(final Map<String, String> chessBoard) {
		this.chessBoard = chessBoard;
	}

	public Map<String, String> getChessBoard() {
		return chessBoard;
	}

}
