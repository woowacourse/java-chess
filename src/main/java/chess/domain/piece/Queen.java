package chess.domain.piece;

import chess.domain.board.Position;

public class Queen extends Piece {

	public Queen(final Team team) {
		super(team);
	}

	@Override
	protected String createSymbol(final Team team) {
		if (team.isBlack()) {
			return "Q";
		}
		return "q";
	}

	@Override
	public void validateMovement(final Position source, final Position target) {
		if (target.isDifferentDiagonal(source) && target.isDifferentRow(source) && target.isDifferentColumn(source)) {
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
		return 9;
	}
}
