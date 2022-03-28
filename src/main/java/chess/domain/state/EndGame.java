package chess.domain.state;

import chess.domain.piece.Team;

public final class EndGame extends Finished {

	EndGame(final Team winner) {
		super(winner);
	}
}
