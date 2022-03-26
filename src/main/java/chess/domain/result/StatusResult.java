package chess.domain.result;

import chess.domain.piece.Team;

public class StatusResult {

	private final double blackScore;
	private final double whiteScore;

	public StatusResult(double blackScore, double whiteScore) {
		this.blackScore = blackScore;
		this.whiteScore = whiteScore;
	}

	public double getBlackScore() {
		return blackScore;
	}

	public double getWhiteScore() {
		return whiteScore;
	}

	public Team getWinner() {
		if (blackScore > whiteScore) {
			return Team.BLACK;
		}
		if (blackScore == whiteScore) {
			return Team.NEUTRALITY;
		}
		return Team.WHITE;
	}
}
