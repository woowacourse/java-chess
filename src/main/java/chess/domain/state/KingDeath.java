package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.piece.Team;

public final class KingDeath extends Finished {

	KingDeath(final Board board, final Team winner) {
		super(board, winner);
	}
}
