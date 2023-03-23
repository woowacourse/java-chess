package chess.domain.result;

import chess.domain.Team;

public class FinalResult implements Result {

	private static final String NO_SCORE_IN_FINAL_RESULT_ERROR_MESSAGE = "점수가 없는 결과입니다.";

	private final Team winner;

	public FinalResult(final Team winner) {
		this.winner = winner;
	}

	@Override
	public Team getWinner() {
		return winner;
	}

	@Override
	public int getWhiteScoreMultipliedBy10() {
		throw new UnsupportedOperationException(NO_SCORE_IN_FINAL_RESULT_ERROR_MESSAGE);
	}

	@Override
	public int getBlackScoreMultipliedBy10() {
		throw new UnsupportedOperationException(NO_SCORE_IN_FINAL_RESULT_ERROR_MESSAGE);
	}
}
