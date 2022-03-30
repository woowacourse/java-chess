package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Team;

public final class WhiteTurn extends Running {

	public WhiteTurn(final Board board) {
		super(board);
	}

	@Override
	protected void validateTurn(final Position position) {
		if (!board.isAlly(position, Team.WHITE)) {
			throw new IllegalArgumentException(WRONG_SOURCE_ERROR);
		}
	}

	@Override
	protected State getNextTurn(boolean check) {
		if (check) {
			return new KingDeath(board, Team.WHITE);
		}
		return new BlackTurn(board);
	}
}
