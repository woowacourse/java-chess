package web.dto;

import java.util.List;

public class BoardDto {
	private List<String> Piece;

	public BoardDto(List<String> piece) {
		Piece = piece;
	}

	public List<String> getPiece() {
		return Piece;
	}
}
