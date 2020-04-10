package chess.dto;

import chess.domain.piece.Color;

public class ScoreDto {
	private Color color;
	private double score;

	public ScoreDto(Color color, double score) {
		this.color = color;
		this.score = score;
	}

	public String getColor() {
		return color.name();
	}

	public double getScore() {
		return score;
	}
}
