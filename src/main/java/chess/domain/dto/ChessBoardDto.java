package chess.domain.dto;

import java.util.List;

public class ChessBoardDto {
	private List<List<String>> chessboard;

	public ChessBoardDto(List<List<String>> chessboard) {
		this.chessboard = chessboard;
	}

	public List<List<String>> getChessboard() {
		return chessboard;
	}
}
