package chess.dto;

import chess.domain.Status;

public class ScoreDto {
	private final double blackScore;
	private final double whiteScore;

	public ScoreDto(Status status) {
		this.blackScore = status.getBlackScore();
		this.whiteScore = status.getWhiteScore();
	}

	public double getBlackScore() {
		return blackScore;
	}

	public double getWhiteScore() {
		return whiteScore;
	}
}
