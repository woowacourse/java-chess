package chess.move;

import java.util.List;

import chess.Board;
import chess.position.Position;

public abstract class MoveValidateStrategy {
	protected Board board;

	public MoveValidateStrategy(Board board) {
		this.board = board;
	}

	public abstract boolean isMovable(List<Position> traces);
}
