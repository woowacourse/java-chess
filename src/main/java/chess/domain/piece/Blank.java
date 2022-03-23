package chess.domain.piece;

import chess.domain.board.Position;

public class Blank extends Piece {

	public Blank() {
		super(Team.NEUTRALITY);
	}

	@Override
	protected String createSymbol(final Team team) {
		return ".";
	}

	@Override
	public void validateMovement(final Position source, final Position target) {
		throw new IllegalArgumentException("빈 곳은 이동이 불가능 합니다.");
	}

	@Override
	public boolean isBlank() {
		return true;
	}
}
