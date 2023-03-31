package chess.domain.result;

import chess.domain.Team;

public class FinalResult {

	private final Team winner;

	public FinalResult(final Team winner) {
		this.winner = winner;
	}

	public Team getWinner() {
		return winner;
	}
}
