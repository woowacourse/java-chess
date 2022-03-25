package chess.domain.piece;

import chess.domain.board.Position;

public class Bishop extends Piece {

	public Bishop(final Team team) {
		super(team);
	}

	@Override
	protected String createSymbol(final Team team) {
		if (team.isBlack()) {
			return "B";
		}
		return "b";
	}

	@Override
	public void validateMovement(final Position source, final Position target) {
		if (target.isDifferentDiagonal(source)) {
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
	public double getScore() {
		return 3;
	}
}
