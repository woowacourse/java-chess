package web.dto;

import java.util.List;
import java.util.Map;

public class BoardDto {
	private List<String> Piece;
	private Map<String,Double> score;

	public BoardDto(List<String> piece, Map<String, Double> score) {
		Piece = piece;
		this.score = score;
	}

	public List<String> getPiece() {
		return Piece;
	}

	public Map<String, Double> getScore() {
		return score;
	}
}
