package chess.domain.piece;

import java.util.function.Consumer;

import chess.domain.Color;
import chess.domain.board.Position;

public final class Pawn extends Piece {
	private static final int MOVABLE_DISTANCE_AT_FIRST_TURN = 2;
	private static final int MOVABLE_DISTANCE = 1;
	private static final double SCORE = 1;

	private boolean firstMove;

	public Pawn(Color color) {
		super(color);
		this.firstMove = true;
	}

	@Override
	public void move(Position beforePosition, Position afterPosition, Consumer<Piece> moveFunction) {
		if (!canMove(beforePosition, afterPosition)) {
			throw new IllegalArgumentException(INVALID_TARGET_POSITION_EXCEPTION);
		}
		moveFunction.accept(this);
		this.firstMove = false;
	}

	@Override
	public void capture(Position beforePosition, Position afterPosition, Consumer<Piece> moveFunction) {
		if (!canCapture(beforePosition, afterPosition)) {
			throw new IllegalArgumentException(INVALID_TARGET_POSITION_EXCEPTION);
		}
		moveFunction.accept(this);
		this.firstMove = false;
	}

	private boolean canCapture(Position beforePosition, Position afterPosition) {
		int columnDistance = afterPosition.columnDistance(beforePosition);
		int rowDistance = afterPosition.rowDirectedDistance(beforePosition);
		return columnDistance == MOVABLE_DISTANCE && checkMovableLimitByCamp(rowDistance, MOVABLE_DISTANCE);
	}

	@Override
	protected boolean canMove(Position beforePosition, Position afterPosition) {
		int rowDirectedDistance = afterPosition.rowDirectedDistance(beforePosition);
		int columnDistance = afterPosition.columnDistance(beforePosition);
		if (columnDistance != 0) {
			return false;
		}
		if (firstMove) {
			return checkMovableLimitByCamp(rowDirectedDistance, MOVABLE_DISTANCE_AT_FIRST_TURN);
		}
		return checkMovableLimitByCamp(rowDirectedDistance, MOVABLE_DISTANCE);

	}

	private boolean checkMovableLimitByCamp(int distance, int movableDistance) {
		if (this.isBlack()) {
			return -movableDistance <= distance && distance < 0;
		}
		return 0 < distance && distance <= movableDistance;
	}

	@Override
	public double getScore() {
		return SCORE;
	}

	@Override
	public String getSymbol() {
		return "p";
	}

	@Override
	public boolean isPawn() {
		return true;
	}
}
