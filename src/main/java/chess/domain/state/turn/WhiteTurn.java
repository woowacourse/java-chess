package chess.domain.state;

import chess.domain.piece.Team;

public final class WhiteTurn extends Running {

	public WhiteTurn() {
		super(Team.WHITE);
	}

	@Override
	protected State next() {
		return new BlackTurn();
	}
}
