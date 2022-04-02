package chess.domain.score;

import chess.domain.piece.Team;

public class ScoreResult {

	private final double blackScore;
	private final double whiteScore;

	public ScoreResult(double blackScore, double whiteScore) {
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
