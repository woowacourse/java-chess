package chess.domain.state.turn;

import chess.domain.Team;

public final class WhiteTurn extends Running {

	public WhiteTurn() {
		super(Team.WHITE);
	}

	@Override
	protected State next() {
		return new BlackTurn();
	}
}
