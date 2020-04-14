package web.dto;

import java.util.List;

public class ResponseDto {
	private List<String> Piece;

	public ResponseDto(List<String> piece) {
		Piece = piece;
	}

	public List<String> getPiece() {
		return Piece;
	}
}
