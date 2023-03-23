package chess.controller;

public class TempResultDto {

	private final String winner;
	private final String whiteScore;
	private final String blackScore;

	public TempResultDto(final String winner, final String whiteScore, final String blackScore) {
		this.winner = winner;
		this.whiteScore = whiteScore;
		this.blackScore = blackScore;
	}

	public String getWinner() {
		return winner;
	}

	public String getWhiteScore() {
		return whiteScore;
	}

	public String getBlackScore() {
		return blackScore;
	}
}
