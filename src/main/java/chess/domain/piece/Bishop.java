package chess.domain.piece;

import chess.domain.board.Position;

public final class Bishop extends Piece {
	
	private static final int BISHOP_SCORE = 3;

	public Bishop(final Team team) {
		super(team);
	}

	@Override
	protected void validateDirection(final Position source, final Position target, final Piece targetPiece) {
		if (!target.isDiagonalMove(source)) {
			throw new IllegalArgumentException(MOVEMENT_ERROR);
		}
	}

	@Override
	public boolean isBlank() {
		return false;
	}

	@Override
	public boolean isPawn() {
		return false;
	}

	@Override
	public boolean isKing() {
		return false;
	}

	@Override
	public double getScore() {
		return BISHOP_SCORE;
	}
}
