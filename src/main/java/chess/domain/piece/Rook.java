package chess.domain.piece;

import chess.domain.board.Position;

public class Rook extends Piece {

	public Rook(final Team team) {
		super(team);
	}

	@Override
	protected String createSymbol(final Team team) {
		if (team.isBlack()) {
			return "R";
		}
		return "r";
	}

	@Override
	public void validateMovement(final Position source, final Position target) {
		if (target.isDifferentRow(source) && target.isDifferentColumn(source)) {
			throw new IllegalArgumentException(MOVEMENT_ERROR);
		}
	}

	@Override
	public boolean isBlank() {
		return false;
	}

}
