package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Team;

public final class BlackTurn extends Running {

	BlackTurn(final Board board) {
		super(board);
	}

	@Override
	protected void validateTurn(final Position position) {
		if (!board.isAlly(position, Team.BLACK)) {
			throw new IllegalArgumentException(WRONG_SOURCE_ERROR);
		}
	}

	@Override
	protected State getNextTurn(boolean check) {
		if (check) {
			return new KingDeath(board, Team.BLACK);
		}
		return new WhiteTurn(board);
	}
}
