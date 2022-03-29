package chess.domain.piece;

import java.util.function.Consumer;

import chess.domain.Color;
import chess.domain.board.Position;

public final class Knight extends Piece {
	private static final int FIRST_MOVABLE_DISTANCE = 2;
	private static final int SECOND_MOVABLE_DISTANCE = 1;
	private static final double SCORE = 2.5;

	public Knight(Color color) {
		super(color);
	}

	@Override
	public void move(Position beforePosition, Position afterPosition, Consumer<Piece> moveFunction) {
		if (!canMove(beforePosition, afterPosition)) {
			throw new IllegalArgumentException(INVALID_TARGET_POSITION_EXCEPTION);
		}
		moveFunction.accept(this);
	}

	@Override
	public void capture(Position beforePosition, Position afterPosition, Consumer<Piece> moveFunction) {
		this.move(beforePosition, afterPosition, moveFunction);
	}

	@Override
	protected boolean canMove(Position beforePosition, Position afterPosition) {
		int columnDistance = beforePosition.columnDistance(afterPosition);
		int rowDistance = beforePosition.rowDistance(afterPosition);
		if (rowDistance == FIRST_MOVABLE_DISTANCE && columnDistance == SECOND_MOVABLE_DISTANCE) {
			return true;
		}
		return columnDistance == FIRST_MOVABLE_DISTANCE && rowDistance == SECOND_MOVABLE_DISTANCE;
	}

	@Override
	public double getScore() {
		return SCORE;
	}

	@Override
	public boolean isBishop() {
		return false;
	}

	@Override
	public boolean isKing() {
		return false;
	}

	@Override
	public boolean isKnight() {
		return true;
	}

	@Override
	public boolean isPawn() {
		return false;
	}

	@Override
	public boolean isQueen() {
		return false;
	}

	@Override
	public boolean isRook() {
		return false;
	}
}
