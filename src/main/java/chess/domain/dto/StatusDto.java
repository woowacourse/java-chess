package chess.domain.dto;

public class StatusDto {
	private double whiteScore;
	private double blackScore;

	public StatusDto(double whiteScore, double blackScore) {
		this.whiteScore = whiteScore;
		this.blackScore = blackScore;
	}

	public double getWhiteScore() {
		return whiteScore;
	}

	public double getBlackScore() {
		return blackScore;
	}
}
