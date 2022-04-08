package chess.domain.piece;

import chess.domain.board.Position;

public final class Rook extends Piece {

	private static final int ROOK_SCORE = 5;
	private static final String symbol = "rook";

	public Rook(final Team team) {
		super(team, symbol);
	}

	@Override
	protected void validateDirection(final Position source, final Position target, final Piece targetPiece) {
		if (!target.isLinerMove(source)) {
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
		return ROOK_SCORE;
	}
}
