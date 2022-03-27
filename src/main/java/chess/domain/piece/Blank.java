package chess.domain.piece;

import chess.domain.board.Position;

public class Blank extends Piece {

	private static final String SYMBOL = ".";
	private static final String CAN_NOT_MOVE_ERROR = "빈 곳은 이동이 불가능 합니다.";

	public Blank() {
		super(Team.NEUTRALITY);
	}

	@Override
	protected String createSymbol(final Team team) {
		return SYMBOL;
	}

	@Override
	protected void validateDirection(final Position source, final Position target, final Piece targetPiece) {
		throw new IllegalArgumentException(CAN_NOT_MOVE_ERROR);
	}

	@Override
	public boolean isBlank() {
		return true;
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
		return 0;
	}
}
