package chess.domain;

import chess.domain.position.Position;
import chess.domain.position.Row;

public enum Side {
	BLACK(Row.TWO),
	WHITE(Row.SEVEN);

	private Row initPawnRow;

	Side(Row initPawnRow) {
		this.initPawnRow = initPawnRow;
	}

	public boolean isInitPawnRow(Position position){
		return position.isSameRow(initPawnRow);
	}
}
