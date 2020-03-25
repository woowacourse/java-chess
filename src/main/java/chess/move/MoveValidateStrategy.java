package chess.move;

import chess.Board;
import chess.position.Position;

import java.util.List;

public abstract class MoveValidateStrategy {
	protected Board board;

	public MoveValidateStrategy(Board board) {
		this.board = board;
	}

	public abstract boolean isMovable(List<Position> traces);
}
