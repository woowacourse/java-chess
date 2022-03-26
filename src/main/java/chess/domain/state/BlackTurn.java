package chess.domain.state;

import chess.domain.piece.Team;

public final class BlackTurn extends Running {

	BlackTurn() {
		super(Team.BLACK);
	}

	@Override
	protected State getNextTurn() {
		return new WhiteTurn();
	}
}
