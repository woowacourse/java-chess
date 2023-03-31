package chess.dto;

import java.util.List;

public class ViewBoardDto {

	private final List<List<String>> board;
	private final String turn;

	public ViewBoardDto(final List<List<String>> board, final String turn) {
		this.board = board;
		this.turn = turn;
	}

	public List<List<String>> getBoard() {
		return board;
	}

	public String getTurn() {
		return turn;
	}
}
