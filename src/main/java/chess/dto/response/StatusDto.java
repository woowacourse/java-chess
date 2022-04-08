package chess.dto.response;

import chess.domain.piece.Team;
import chess.domain.score.ScoreResult;

public class StatusDto {

	private final double blackScore;
	private final double whiteScore;
	private final String winner;

	private StatusDto(final double blackScore, final double whiteScore, final Team winner) {
		this.blackScore = blackScore;
		this.whiteScore = whiteScore;
		this.winner = winner.getValue();
	}

	public static StatusDto of(final ScoreResult scoreResult) {
		return new StatusDto(scoreResult.getBlackScore(), scoreResult.getWhiteScore(), scoreResult.getWinner());
	}

	public double getBlackScore() {
		return blackScore;
	}

	public double getWhiteScore() {
		return whiteScore;
	}

	public String getWinner() {
		return winner;
	}
}
