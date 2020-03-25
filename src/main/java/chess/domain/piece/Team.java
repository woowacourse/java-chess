package chess.domain.piece;

import chess.domain.position.Row;

public enum Team {
	WHITE(-1, Row.SEVEN, Row.FIVE),
	BLACK(1, Row.TWO, Row.FOUR);

	private final int forwardDirection;
	private final Row initialPawnRow;
	private final Row specialPawnRow;

	Team(int forwardDirection, Row initialPawnRow, Row specialPawnRow) {
		this.forwardDirection = forwardDirection;
		this.initialPawnRow = initialPawnRow;
		this.specialPawnRow = specialPawnRow;
	}

	public int getForwardDirection() {
		return forwardDirection;
	}

	public boolean isInitialPawnRow(Row row) {
		return initialPawnRow.equals(row);
	}

	public Row getSpecialMoveOfPawn() {
		return specialPawnRow;
	}
}
