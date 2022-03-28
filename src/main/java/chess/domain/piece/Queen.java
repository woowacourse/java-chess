package chess.domain.piece;

import java.util.function.Consumer;

import chess.domain.Camp;
import chess.domain.board.Position;

public final class Queen extends Piece {
	private static final int NOT_MOVED_DISTANCE = 0;
	private static final double SCORE = 9;

	public Queen(Camp camp) {
		super(camp);
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
		if (columnDistance == NOT_MOVED_DISTANCE) {
			return true;
		}
		if (rowDistance == NOT_MOVED_DISTANCE) {
			return true;
		}
		return columnDistance == rowDistance;
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
		return false;
	}

	@Override
	public boolean isPawn() {
		return false;
	}

	@Override
	public boolean isQueen() {
		return true;
	}

	@Override
	public boolean isRook() {
		return false;
	}
}
