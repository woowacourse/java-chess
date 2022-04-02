package chess.domain.piece;

import chess.domain.board.Position;

public final class Blank extends Piece {

	private static final String CAN_NOT_MOVE_ERROR = "해당 위치에 기물이 없어 움직일 수 없습니다.";
	private static final int BLANK_SCORE = 0;

	public Blank() {
		super(Team.NEUTRALITY);
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
		return BLANK_SCORE;
	}
}
