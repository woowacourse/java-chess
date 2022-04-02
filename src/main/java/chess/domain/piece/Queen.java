package chess.domain.piece;

import chess.domain.board.Position;

public final class Queen extends Piece {

	private static final int QUEEN_SCORE = 9;

	public Queen(final Team team) {
		super(team);
	}
	
	@Override
	protected void validateDirection(final Position source, final Position target, final Piece targetPiece) {
		if (!target.isDiagonalMove(source) && !target.isLinerMove(source)) {
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
		return QUEEN_SCORE;
	}
}
