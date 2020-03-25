package chess.move;

import chess.Board;

public abstract class MoveValidateStrategy {
	protected Board board;

	public MoveValidateStrategy(Board board) {
		this.board = board;
	}
}
