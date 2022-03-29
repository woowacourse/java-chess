package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

public final class BlackTurn extends Running {

	BlackTurn(final Board board) {
		super(board);
	}

	@Override
	protected void validateTurn(final Piece piece) {
		if (!piece.isAlly(Team.BLACK)) {
			throw new IllegalArgumentException(WRONG_SOURCE_ERROR);
		}
	}

	@Override
	protected State getNextTurn(boolean kingDeath) {
		if (kingDeath) {
			return new KingDeath(board, Team.BLACK);
		}
		return new WhiteTurn(board);
	}
}
