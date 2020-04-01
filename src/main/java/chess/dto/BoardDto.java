package chess.dto;

import java.util.List;

public class BoardDto {
	private List<String> board;

	public BoardDto(List<String> board) {
		this.board = board;
	}

	public List<String> getBoard() {
		return board;
	}

	public void setBoard(List<String> board) {
		this.board = board;
	}
}
